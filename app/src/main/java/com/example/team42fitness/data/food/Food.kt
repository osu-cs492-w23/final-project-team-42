package com.example.team42fitness.data.food

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Food(
    @PrimaryKey
    val id: Int,
    val fdcid: String,
    val date: String
) : java.io.Serializable