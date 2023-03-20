package com.example.team42fitness.ui.fitness

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.fitnessData.LocationData
import com.google.android.material.snackbar.Snackbar

class ClickedDayFragment : Fragment(R.layout.fragment_clicked_day)
{
    private val TAG = "ClickedDayFragment.kt"

    // private val viewModel: ClickedDayViewModel by viewModels()

    private val roomViewModel: ClickedDayViewModel by viewModels()
    private val locationEntriesAdapter = ClickedDayAdapter(::onLocationEntryClick)

    private val args: ClickedDayFragmentArgs by navArgs()

    private lateinit var locationEntryRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        /**
         * RecyclerView set up for user location entries
         */
        locationEntryRV = view.findViewById(R.id.rv_clicked_day)
        locationEntryRV.layoutManager = LinearLayoutManager(requireContext())
        locationEntryRV.setHasFixedSize(true)
        locationEntryRV.adapter = locationEntriesAdapter



        val locationEntryET: EditText = view.findViewById(R.id.et_location_entry)
        val locationEntryBtn: Button = view.findViewById(R.id.btn_add_entry)

        locationEntryBtn.setOnClickListener {
            val newLocationEntry = locationEntryET.text.toString()
            if (!TextUtils.isEmpty(newLocationEntry))
            {
                locationEntriesAdapter.addLocationEntry(LocationData(newLocationEntry))
                locationEntryET.setText("")
                roomViewModel.addLocationEntry(LocationData(newLocationEntry))
            }
        }
    }




    /* launch intent to google maps for the location entry clicked */
    private fun onLocationEntryClick(locationData: LocationData)
    {
        viewLocationEntryOnMap(locationData)
    }


    private fun viewLocationEntryOnMap(locationData: LocationData)
    {
        Log.d(TAG, "ViewLocationEntryOnMap() called on ${args.locationDate} for the entry $locationData")

    }
}