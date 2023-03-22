package com.example.team42fitness.data.activity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FitnessActivity(
    @PrimaryKey
    val id: Int,
    val latitude: String,
    val longitude: String,
    val location: String,
    val steps: Int,
    val date: String
    ) : java.io.Serializable