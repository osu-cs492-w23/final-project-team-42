package com.example.team42fitness.data.food

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Food(
    @PrimaryKey
    val fdcid: Int,
    val name: String,
    val energy: String?,
    val protein: String?,
    val fat: String?,
    val carbs: String?,
    val sugars: String?,
    val date: String?

) : java.io.Serializable