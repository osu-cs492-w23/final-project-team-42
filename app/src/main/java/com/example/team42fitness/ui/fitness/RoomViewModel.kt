package com.example.team42fitness.ui.fitness

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.team42fitness.data.AppDatabase
import com.example.team42fitness.data.fitnessData.LocationData
import com.example.team42fitness.data.fitnessData.LocationRepository
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

/**
 * AndroidViewModel to communicate with db when at screen for specific day
 */
class RoomViewModel(application: Application): AndroidViewModel(application)
{
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

    fun getLocationEntriesFromSpecificDay(day: String)
    {
        viewModelScope.launch{
            repository.getLocationEntriesFromSpecificDay(day)
        }
    }
}