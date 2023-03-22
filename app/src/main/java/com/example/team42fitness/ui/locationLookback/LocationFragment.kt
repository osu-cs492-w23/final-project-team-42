package com.example.team42fitness.ui.locationLookback

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.locationLookback.LocationDate
import com.example.team42fitness.ui.FitnessActivitiesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * The LocationFragment class will be used to display a list of items to represent each day
 * using RecyclerView. Clicking on a specific item will bring the user to another fragment,
 * where they can enter in locations they went to during a particular day as entries. These will
 * be displayed as a list in that fragment, where clicking on a specific one will launch an intent
 * to show that entry's location. Another functionality to be implemented is clicking a button
 * to show all those entries entered by the user as markers on a Google map embedded into that fragment.
 */
class LocationFragment : Fragment(R.layout.fragment_location)
{
    private val TAG = "LocationFragment.kt"

    private val viewModel: LocationViewModel by viewModels()
    private val fviewModel: FitnessActivitiesViewModel by viewModels()
    private val locationAdapter = LocationAdapter(::onLocationDateItemClick)

    private lateinit var locationDateRV: RecyclerView

    private var counter = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        // Go to record fitness fragment on floating button click
        view.findViewById<FloatingActionButton>(R.id.button_record_fitness).setOnClickListener {
            val directions = LocationFragmentDirections.navigateToRecordFitness()
            findNavController().navigate(directions)
        }

        /**
         * RecyclerView setup
         */
        locationDateRV = view.findViewById(R.id.rv_location_list)
        locationDateRV.layoutManager = LinearLayoutManager(requireContext())
        locationDateRV.setHasFixedSize(true)
        locationDateRV.adapter = locationAdapter


        fviewModel.fitnessActivities.observe(requireActivity()) {
            locationAdapter.addFitnessActivities(it)
        }


        /**
         * Populate recyclerview (for now, not sure if may change later)

        if (counter == 1)
        {
            val listDates: List<String> = populateList(start = "03-19-2023", end = "04-02-2023")
            for (day in listDates) {
                locationAdapter.addDate(LocationDate(day))
                Log.d(TAG, day)
            }
        }
            counter += 1
         */


    }



    // https://stackoverflow.com/questions/11412713/generate-dates-between-two-date-in-android
    // https://www.baeldung.com/kotlin/string-to-date
    private fun populateList(start: String, end: String): MutableList<String>
    {
        val startOfCal = Calendar.getInstance()
        val endOfCal = Calendar.getInstance()

        val listOfDates: MutableList<String> = mutableListOf()

        val formatDate = SimpleDateFormat("MM-dd-yyyy")


         startOfCal.time = formatDate.parse(start)
         endOfCal.time = formatDate.parse(end)


        while(startOfCal.before(endOfCal))
        {
            val stringToTokenize: String = startOfCal.time.toString()
            val tokensInArray: List<String> = stringToTokenize.split(" ")
            val stringIWant: String = tokensInArray[0] + " " + tokensInArray[1] + " " + tokensInArray[2]

            listOfDates.add(stringIWant)
            startOfCal.add(Calendar.DATE, 1)
        }

        return listOfDates
    }




    private fun onLocationDateItemClick(locationDate: LocationDate)
    {
        Log.d(TAG, "onLocationDateItemClick() called, locationDate: $locationDate")

        val directions = LocationFragmentDirections.navigateToClickedDay(locationDate)
        findNavController().navigate(directions)
    }


}