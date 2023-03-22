package com.example.team42fitness.ui.locationLookback

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.team42fitness.R
import com.example.team42fitness.api.LocationFetcher
import com.example.team42fitness.api.StepCounter
import com.example.team42fitness.data.locationLookback.LocationData

class RecordFitnessFragment : Fragment(R.layout.fragment_record_fitness) {
    private lateinit var viewModel: RecordFitnessViewModel

    private var isRecording = false
    private var locLat: Double = 0.0
    private var locLong: Double = 0.0

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

                // TODO: Enter data into database

                btnRecordSteps.text = "Record Steps"
            }
        }
    }
}