package com.example.team42fitness.data.foodData

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson


@JsonClass(generateAdapter = true)
@Entity
data class FoodItem(
    @PrimaryKey
    val fdcId: Int,

    val description: String,

    @Json(name="foodNutrients")
    val nutrients: List<Nutrients>,

    ): java.io.Serializable


@JsonClass(generateAdapter = true)
data class Nutrients (
    @Json(name="nutrientName")
    val name: String,

    @Json(name="unitName")
    val unit: String,

    @Json(name="value")
    val amount: Float,

    ): java.io.Serializable

@JsonClass(generateAdapter = true)
data class FoodDataPropertiesJson(
    val fdcId: Int, // Unique ID of the food

    val description: String, // The Description of the food
    val foodNutrients: List<Nutrients>
)

class FoodListJsonAdapter {
    @FromJson
    fun FoodListFromJson(list: FoodDataPropertiesJson) = FoodItem(
        fdcId = list.fdcId,
        description = list.description,
        nutrients = list.foodNutrients,
    )

    @ToJson
    fun FoodListToJson(food: FoodItem): String {
        throw UnsupportedOperationException("encoding FoodItem to JSON is not supported")
    }
}
