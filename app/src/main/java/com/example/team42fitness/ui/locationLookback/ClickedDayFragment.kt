package com.example.team42fitness.ui.locationLookback

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.locationLookback.LocationData
import com.google.android.material.snackbar.Snackbar

/**
 * The ClickedDayFragment will be used to deal with when the user clicks on a specific day
 * to add locations of where they went to on a particular day. Users are able to add entries
 * on locations they went to, which will be displayed in a recyclerview. When the user clicks
 * on a particular entry, an intent will be launched to view that location. The hope is that
 * there will be functionality added as well, where, at the click of a button, the user
 * will be able to see all the entries as markers on an embedded Google Maps object.
 */
class ClickedDayFragment : Fragment(R.layout.fragment_clicked_day)
{
    private val TAG = "ClickedDayFragment.kt"

    /**
     * Maybe utilizing this viewModel will help with the data persisting issue when going between screens...
     */
    private val viewModel: ClickedDayViewModel by viewModels()
    private val args: ClickedDayFragmentArgs by navArgs()

    private val roomViewModel: RoomViewModel by viewModels()
    private val locationEntriesAdapter = ClickedDayAdapter(::onLocationEntryClick)

    private lateinit var locationEntryRV: RecyclerView

    /**
     * To keep track of entries added and to act as primary key since it can't be replaced by an entry.
     */
    private var insertCounter = 0

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


        /**
         * Override support action bar title to include the date, based on which day was clicked
         */
        var fullSupportActionBarTitle: String = args.locationDate.date + " " + "Lookback"
        (activity as AppCompatActivity).supportActionBar?.title = fullSupportActionBarTitle

        setRetainInstance(true)

        /**
         * Wondering if I gotta read from database to populate adapter whenever a day is clicked to repopulate...
         * Since it looks like content is not displayed if I go back and then return to specific day...
         * Am drawing blanks rn with all the files...
         */

//        entriesFromSpecificDate?.let {
//            for (item in it) {
//                locationEntriesAdapter.addLocationEntry(LocationData(item.index, item.day, item.locationName))
//            }
//        }
        // locationEntriesAdapter.addLocationEntry()


        val locationEntryET: EditText = view.findViewById(R.id.et_location_entry)
        val locationEntryBtn: Button = view.findViewById(R.id.btn_add_entry)

        /**
         * OnClickListener for button to add a location entry when the user
         * has entered a location they went to that day. Also adds entry info
         * into database, which includes index, day, and location entry
         */
        locationEntryBtn.setOnClickListener {
            var newLocationEntry = locationEntryET.text.toString()
            if (!TextUtils.isEmpty(newLocationEntry))
            {
                locationEntriesAdapter.addLocationEntry(LocationData(insertCounter, args.locationDate.date, newLocationEntry))
                roomViewModel.addLocationEntry(LocationData(insertCounter, args.locationDate.date, newLocationEntry))

                locationEntryET.setText("")

                insertCounter += 1
            }
        }


        /**
         * Functionality to delete entry. Removes it from recyclerview but currently not set up to remove from db
         */
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
        {
            override fun onMove (
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder)
                    : Boolean { return false }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
            {
                /**
                 * See how to access this specific location entry...
                 * Maybe I have to change or add LocationData as an
                 * args so that I can easily access the data.
                 * However, it was messing things up so yeah...
                 */
                // val entry = roomViewModel.getSingleLocationEntry()

                /**
                 * 'position' would be an items place in terms of entry order for a specific day,
                 * so wouldn't necessarily correspond to index in db... so there may be issues there...
                 */
                val position = viewHolder.absoluteAdapterPosition
                locationEntriesAdapter.deleteLocationEntry(position)
                // roomViewModel.deleteLocationEntry(LocationData(, , ))
            }


        }

        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(locationEntryRV)

    }



    override fun onResume()
    {
        Log.d(TAG, "onResume() called!!")
        super.onResume()

        var fullSupportActionBarTitle: String = args.locationDate.date + " " + "Lookback"
        (activity as AppCompatActivity).supportActionBar?.title = fullSupportActionBarTitle

        setRetainInstance(true)


        // add functionality that gets entries from database for specific day (based on which day was clicked) and puts that into recyclerview
        // unless, that has to be handled by ClickedDayViewModel


        roomViewModel.setDay(args.locationDate.date)
//        val entriesFromSpecificDay = roomViewModel.locationEntriesFromSpecificDay

        Log.d(TAG, "onResume(), passed roomViewModel.setDay()")


        val testList = mutableListOf<String>()

        roomViewModel.locationEntriesFromSpecificDay.observe(this)
        {
            for (entry in it)
            {
                // locationEntriesAdapter.addLocationEntry(LocationData(entry.index, entry.day, entry.locationName))
                Log.d(TAG, entry.toString())
                testList.add(entry.locationName)
            }
        }

        Log.d(TAG, "onResume(), passed for loop for entries from specific day (hopefully)")


        Log.d(TAG, "after for loop. hopefully contents returned. Now printing contents of testList")
        for (item in testList)
        {
            print(item)
        }

    }


    /**
     *  call to viewLocationEntryOnMap to handle intents to view location entry on map
     */
    private fun onLocationEntryClick(locationData: LocationData)
    {
        viewLocationEntryOnMap(locationData)
    }


    /**
     * Haunch intent to google maps for the location entry clicked
     */
    // https://developer.android.com/guide/components/intents-common
    // https://stackoverflow.com/questions/3574644/how-can-i-find-the-latitude-and-longitude-from-address
    private fun viewLocationEntryOnMap(locationData: LocationData)
    {
        Log.d(TAG, "ViewLocationEntryOnMap() called on ${args.locationDate.date} for the entry $locationData")

        val uriBase = "geo:0,0?q="
        val uri: String = uriBase + locationData.locationName

        val geoUri = Uri.parse(uri)

        val intent = Intent(Intent.ACTION_VIEW, geoUri)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Snackbar.make(
                requireActivity().findViewById(R.id.coordinator_layout),
                "An app wasn't found to be able to open and display the location for the entry you entered... :(",
                Snackbar.LENGTH_LONG
            ).show()
        }

    }
}