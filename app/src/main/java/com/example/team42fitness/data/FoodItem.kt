package com.example.team42fitness.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import java.io.Serializable

class FoodItem (
    val fdcId: Int,
    val description: String,
        ) : Serializable

/* ******************************************************************************************
 * Below is a set of classes used to parse the JSON response from the OpenWeather API into
 * a ForecastPeriod object.  The first several classes are designed to match the structure
 * of one element of the `list` field in the OpenWeather 5-day forecast API's JSON response.
 * The last is a custom type adapter that can be used with Moshi to parse OpenWeather JSON
 * directly into a ForecastPeriod object.
 * ******************************************************************************************/

//SearchResultFood:
//      type: object
//      required:
//              - fdcId
//              - description
//      properties:
//              fdcId:
//                      description: Unique ID of the food.
//                      type: integer
//                      example: 45001529
//              dataType:
//                      description: The type of the food data.
//                      type: string
//                      example: "Branded"
//              description:
//                      description: The description of the food.
//                      type: string
//                      example: "BROCCOLI"
//              foodCode:
//                      description: Any A unique ID identifying the food within FNDDS.
//                      type: string
//                      foodNutrients:
//              type: array
//                      items:
//                      $ref: '#/components/schemas/AbridgedFoodNutrient'
//              publicationDate:
//                      description: Date the item was published to FDC.
//                      type: string
//                      example: "4/1/2019"
//              scientificName:
//                      description: The scientific name of the food.
//                      type: string
//              brandOwner:
//                      description: Brand owner for the food. Only applies to Branded Foods.
//                      type: string
//                      example: "Supervalu, Inc."
//              gtinUpc:
//                      description: GTIN or UPC code identifying the food. Only applies to Branded Foods.
//                      type: string
//                      example: "041303020937"
//              ingredients:
//                      description: The list of ingredients (as it appears on the product label). Only applies to Branded Foods.
//                      type: string
//              ndbNumber:
//                      description: Unique number assigned for foundation foods. Only applies to Foundation and SRLegacy Foods.
//                      type: integer
//              additionalDescriptions:
//                      description: Any additional descriptions of the food.
//                      type: string
//                      example: "Coon; sharp cheese; Tillamook; Hoop; Pioneer; New York; Wisconsin; Longhorn"
//              allHighlightFields:
//                      description: allHighlightFields
//                      type: string
//                      score:
//              description: Relative score indicating how well the food matches the search criteria.
//                      type: number
//                      format: float

/**
 * This class represents an item in the `list` field of the JSON response from the FoodData Central API.
 */
@JsonClass(generateAdapter = true)
data class FoodDataPropertiesJson(
        val fdcId: Int, // Unique ID of the food
        val dataType: String, // type of food data Ex: "Branded"
        val description: String, // The Description of the food
        val additionalDescriptions: String, // Any additional descriptions of the food
        val score: Float, // relative score indicating how well the food matches the search
)

//@JsonClass(generateAdapter = true)
//data class FoodDataPropertiesFoodNutrientsJson(
//        val items: listOf(String)
//)

/**
 * This class is a custom JSON adapter for use with Moshi.  It uses the classes above to represent
 * and parse the JSON response from the FoodData Central API, then it takes data from the parsed JSON
 * and uses it to build a [FoodItem] object, which becomes the ultimate return value from the
 * JSON parsing.
 */
class FoodListJsonAdapter {
        @FromJson
        fun FoodListFromJson(list: FoodDataPropertiesJson) = FoodItem(
                fdcId = list.fdcId,
                description = list.description,
        )

        @ToJson
        fun FoodListToJson(food: FoodItem): String {
                throw UnsupportedOperationException("encoding FoodItem to JSON is not supported")
        }
}