package com.example.team42fitness.api.food

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//SearchResult:
//      properties:
//              foodSearchCriteria:
//              $ref: '#/components/schemas/FoodSearchCriteria'
//      totalHits:
//              description: The total number of foods found matching the search criteria.
//              type: integer
//              example: 1034
//      currentPage:
//              description: The current page of results being returned.
//              type: integer
//      totalPages:
//              description: The total number of pages found matching the search criteria.
//              type: integer
//      foods:
//              description: The list of foods found matching the search criteria. See Food Fields below.
//              type: array
//              items:
//              $ref: '#/components/schemas/SearchResultFood'

@JsonClass(generateAdapter = true)
data class FoodSearchResultsList(
    @Json(name = "foods") val foodsList: List<FoodItem>
) :java.io.Serializable
