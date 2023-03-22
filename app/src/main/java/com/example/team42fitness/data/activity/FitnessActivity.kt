package com.example.team42fitness.data.activity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FitnessActivity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val duration: Int,
    val steps: Int,
    val date: String
    ) : java.io.Serializable