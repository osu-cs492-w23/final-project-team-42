package com.example.team42fitness.data.foodData

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodDate(val text: String): java.io.Serializable
