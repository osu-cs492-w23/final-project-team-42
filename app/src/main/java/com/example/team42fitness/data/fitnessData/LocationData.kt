package com.example.team42fitness.data.fitnessData

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This is the entity component for the Room persistence library
 */
@Entity
data class LocationData
(
//    val index: Int,
    @PrimaryKey val locationName: String
)
