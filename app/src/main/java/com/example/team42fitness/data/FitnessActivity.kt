package com.example.team42fitness.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FitnessActivity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val duration: Int,
    val caloriesBurned: Int,
    val date: String
    ) : java.io.Serializable