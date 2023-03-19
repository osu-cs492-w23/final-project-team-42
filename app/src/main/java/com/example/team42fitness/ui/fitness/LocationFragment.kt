package com.example.team42fitness.ui.fitness

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.fitnessData.LocationDate
import com.example.team42fitness.databinding.FragmentGalleryBinding
import com.example.team42fitness.ui.gallery.GalleryViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

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
    private val TAG="LocationFragment.kt"

    private val viewModel: LocationViewModel by viewModels()
    private val locationAdapter = LocationAdapter(::onLocationDateItemClick)

    private lateinit var locationDateRV: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        /**
         * RecyclerView setup
         */
        locationDateRV = view.findViewById(R.id.rv_location_list)
        locationDateRV.layoutManager = LinearLayoutManager(requireContext())
        locationDateRV.setHasFixedSize(true)
        locationDateRV.adapter = locationAdapter


        /**
         * Populate recyclerview (for now, not sure if may change later)
         */
        val listDates: List<Date> = populateList(start="03-19-2023", end="04-02-2023")
        for (day in listDates)
        {
            locationAdapter.addDate(LocationDate(day.toString()))
        }

    }



    // https://stackoverflow.com/questions/11412713/generate-dates-between-two-date-in-android
    // https://www.baeldung.com/kotlin/string-to-date
    private fun populateList(start: String, end: String): MutableList<Date>
    {
        val startOfCal = Calendar.getInstance()
        val endOfCal = Calendar.getInstance()

        val listOfDates: MutableList<Date> = mutableListOf()
        val formatDate: DateFormat = SimpleDateFormat("MM-dd-yyyy")


        startOfCal.time = formatDate.parse(start)
        endOfCal.time = formatDate.parse(end)

        while(startOfCal.before(endOfCal))
        {
            listOfDates.add(startOfCal.time)
            startOfCal.add(Calendar.DATE, 1)
        }

        return listOfDates
    }




    private fun onLocationDateItemClick(locationDate: LocationDate)
    {
        Log.d(TAG, "onLocationDateItemClick() called, locationDate: $locationDate")

        // val directions = .
    }




}