package com.example.team42fitness.data.locationLookback

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao
{
    /**
     * Insert a single location entry entered by user into database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocationEntry(location: LocationData)


    /**
     * Delete a single location entry from database
     */
    @Delete
    suspend fun deleteLocationEntry(location: LocationData)


    /**
     * Grab all the location entries entered by user from database
     */
    @Query("SELECT * FROM LocationData")
    fun getAllLocationEntries(): Flow<List<LocationData>>


    /**
     * Hopefully the right syntax, but get all the location entries for a specified date passed by variable 'date'
     */
    @Query("SELECT * FROM LocationData WHERE day = :date")
    fun getLocationEntriesFromSpecificDay(date: String): Flow<List<LocationData>>


    /**
     * Grab a single location entry based on index
     */
    // @Query("SELECT * FROM LocationData WHERE index = :chosenIndex")
    // fun getSingleLocationEntry(chosenIndex: Int): Flow<LocationData>
}