package com.example.team42fitness.data.locationLookback

class LocationRepository(private val dao: LocationDao)
{
    suspend fun insertLocationEntry(location: LocationData) = dao.insertLocationEntry(location)

    suspend fun deleteLocationEntry(location: LocationData) = dao.deleteLocationEntry(location)


    fun getAllLocationEntries() = dao.getAllLocationEntries()

    fun getLocationEntriesFromSpecificDay(date: String) = dao.getLocationEntriesFromSpecificDay(date)

    // fun getSingleLocationEntry(index: Int) = dao.getSingleLocationEntry(index)
}