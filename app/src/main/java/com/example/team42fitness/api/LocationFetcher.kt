package com.example.team42fitness.api

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener

class LocationFetcher (private val fragment: Fragment) {
    private val TAG = "LocationFetcher"

    private var fusedLocationClient: FusedLocationProviderClient? = LocationServices.getFusedLocationProviderClient(fragment.requireActivity())
    private var lastCallBackFunc: ((Boolean, Double, Double) -> Unit)? = null
    private val requestPermissionLauncher = fragment.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { isGranted ->
            if (isGranted.containsValue(false)) {
                Log.e(TAG, "User denied permission!")
            } else {
                // Permission is granted. Return the user location
                if (lastCallBackFunc != null) {
                    getCurrentLocation(lastCallBackFunc!!)
                }
            }
        }

    fun getCurrentLocation(callback: (Boolean, Double, Double) -> Unit) {
        if (fusedLocationClient == null) {
            Log.e(TAG, "FusedLocationProviderClient is null!")
            return
        }

        if (ActivityCompat.checkSelfPermission(fragment.requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(fragment.requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            // Need to request permissions
            lastCallBackFunc = callback
            requestPermissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION))
            return
        }

        fusedLocationClient!!.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken =
                CancellationTokenSource().token
            override fun isCancellationRequested() = false
        })
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