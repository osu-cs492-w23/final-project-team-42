package com.example.team42fitness.api

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Debug
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationFetcher (private val activity: AppCompatActivity) {
    private val TAG = "LocationFetcher"

    private var fusedLocationClient: FusedLocationProviderClient? = LocationServices.getFusedLocationProviderClient(activity)
    private var lastCallBackFunc: ((Boolean, Double, Double) -> Unit)? = null
    private val requestPermissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Return the user location
                if (lastCallBackFunc != null) {
                    getCurrentLocation(lastCallBackFunc!!)
                }
            } else {
                Log.e(TAG, "User denied permission!")
            }
        }

    fun getCurrentLocation(callback: (Boolean, Double, Double) -> Unit) {
        if (fusedLocationClient == null) {
            Log.e(TAG, "FusedLocationProviderClient is null!")
            return
        }

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            // Need to request permissions
            lastCallBackFunc = callback
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            return
        }

        fusedLocationClient!!.lastLocation
            .addOnSuccessListener { location : Location? ->
                if (location != null) {
                    callback(true, location.latitude, location.longitude)
                }
                else {
                    Log.e(TAG, "FusedLocationProviderClient.lastLocation returned success but location result is null!")
                    callback(false, 0.0, 0.0)
                }
            }
            .addOnFailureListener {
                Log.e(TAG, "FusedLocationProviderClient.lastLocation returned failure!")
                callback(false, 0.0, 0.0)
            }
    }
}