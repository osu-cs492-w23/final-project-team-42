package com.example.team42fitness.data.foodData

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
@Entity
data class FoodData(
    @Json(name = "fdcId")
    @PrimaryKey
    val id: Int,

    val description: String,

    @Json(name = "foodCategory")
    val category: String,

): java.io.Serializable
