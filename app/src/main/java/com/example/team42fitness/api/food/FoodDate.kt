package com.example.team42fitness.api.food

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodDate(val text: String): java.io.Serializable
