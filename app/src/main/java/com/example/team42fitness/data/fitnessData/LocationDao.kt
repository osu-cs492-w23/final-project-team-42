package com.example.team42fitness.data.fitnessData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao
{
    /**
     * Insert a single location entry entered by user into database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocationEntry(location: LocationData)


    // 🌳🌳🌳🌳🌳🦒❗🌳🌳🌳🌳🌳🦒❗🌳🌳🌳🌳🌳🦒❗
    // (1) have method to delete entry
    // (2) and later to get a specific entry (probably for intent for clicked entry, unless it can be handled elsewhere)


    /**
     * Grab all the location entries entered by user from database
     */
    @Query("SELECT * FROM LocationData")
    fun getAllLocationEntries(): Flow<List<LocationData>>
}