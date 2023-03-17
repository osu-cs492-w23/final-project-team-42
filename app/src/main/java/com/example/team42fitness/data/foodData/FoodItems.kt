package com.example.team42fitness.data.foodData

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodItems(
    @Json(name = "foods") val foodsList: List<FoodData>
)

