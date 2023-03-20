package com.example.team42fitness.data.fitnessData

class LocationRepository(private val dao: LocationDao)
{
    suspend fun insertLocationEntry(location: LocationData) = dao.insertLocationEntry(location)

    // 🌳🌳🌳🌳🌳🦒❗🌳🌳🌳🌳🌳🦒❗🌳🌳🌳🌳🌳🦒❗
    // (1) have method to delete entry
    // (2) and later to get a specific entry (probably for intent for clicked entry, unless it can be handled elsewhere)

    fun getAllLocationEntries() = dao.getAllLocationEntries()

    fun getLocationEntriesFromSpecificDay(date: String) = dao.getLocationEntriesFromSpecificDay(date)
}