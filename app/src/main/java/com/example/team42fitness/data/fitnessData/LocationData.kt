package com.example.team42fitness.data.fitnessData

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This is the entity component for the Room persistence library
 */
@Entity
data class LocationData
(
    @PrimaryKey val index: Int,
    val day: String,
    val locationName: String
) : java.io.Serializable
