package com.example.team42fitness.ui.locationLookback

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.team42fitness.data.AppDatabase
import com.example.team42fitness.data.locationLookback.LocationData
import com.example.team42fitness.data.locationLookback.LocationRepository
import kotlinx.coroutines.launch

/**
 * AndroidViewModel to communicate with db when at screen for specific day
 */
class RoomViewModel(application: Application): AndroidViewModel(application)
{
    private var day: String = ""

    fun setDay(dayPassed: String)
    {
        day = dayPassed
    }

    private val repository = LocationRepository(AppDatabase.getInstance(application).locationDao() )

    /**
     * If I get to the embedded map object functionality I would like to have, use this I think
     */
    val locationEntries = repository.getAllLocationEntries().asLiveData()

    fun addLocationEntry(location: LocationData)
    {
        viewModelScope.launch{
            repository.insertLocationEntry(location)
        }
    }

    fun deleteLocationEntry(location: LocationData)
    {
        viewModelScope.launch {
            repository.deleteLocationEntry(location)
        }
    }


    val locationEntriesFromSpecificDay = repository.getLocationEntriesFromSpecificDay(day).asLiveData()

//    fun getLocationEntriesFromSpecificDay(day: String)
//    {
//        viewModelScope.launch{
//            repository.getLocationEntriesFromSpecificDay(day)
//        }
//    }


    // fun getSingleLocationEntry(index: Int) = repository.getSingleLocationEntry(index).asLiveData()
}