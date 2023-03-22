package com.example.team42fitness.ui.locationLookback

import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.team42fitness.R
import com.example.team42fitness.api.LocationFetcher
import com.example.team42fitness.api.StepCounter
import com.example.team42fitness.data.activity.FitnessActivity
import com.example.team42fitness.ui.FitnessActivitiesViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RecordFitnessFragment : Fragment(R.layout.fragment_record_fitness) {
    private lateinit var viewModel: RecordFitnessViewModel

    private val fviewModel: FitnessActivitiesViewModel by viewModels()

    private var isRecording = false
    private var locLat: Double = 0.0
    private var locLong: Double = 0.0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stepCounter = StepCounter(this)
        val locFetcher = LocationFetcher(this)

        stepCounter.setStepUpdateListener { steps ->
            view.findViewById<TextView>(R.id.textview_steps_counted).text = steps.toString()
        }

        // Record steps button
        val btnRecordSteps = view.findViewById<Button>(R.id.button_record_steps)

        btnRecordSteps.setOnClickListener {
            isRecording = !isRecording
            if (isRecording) {
                // Start recording
                // First get location
                locFetcher.getCurrentLocation { res, lat, long ->
                    if (res) {
                        locLat = lat
                        locLong = long
                    }
                    else {
                        locLat = 0.0
                        locLong = 0.0
                    }
                }

                // Start counting steps
                stepCounter.startRecording()

                btnRecordSteps.text = "Stop Recording"
            }
            else {
                // Stop recording
                val stepsCounted = stepCounter.stopRecording()
                var location: String = "Unknown Location"

                try {
                    val geocoder = Geocoder(requireContext())
                    val addresses = geocoder.getFromLocation(locLat, locLong, 1)

                    if (addresses?.isNotEmpty() == true) {
                        location = addresses!![0].getAddressLine(0)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val date = LocalDateTime.now().format(formatter)

                fviewModel.addFitnessActivity(FitnessActivity(
                    0,
                    locLat.toString(),
                    locLong.toString(),
                    location,
                    stepsCounted,
                    date
                ))

                btnRecordSteps.text = "Record Steps"
            }
        }
    }
}