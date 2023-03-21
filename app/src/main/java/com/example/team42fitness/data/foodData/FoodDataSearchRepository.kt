package com.example.team42fitness.data.foodData

import com.example.team42fitness.api.FoodDataSearchService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSearchRepository (
    private val service: FoodDataSearchService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    /**
     * This method is used to query the USDA FoodDataCentral API's food search method.  It
     * is a suspending function and executes within the coroutine context specified by the
     * `dispatcher` argument to the Repository class's constructor.
     *
     * @param query Specifies the food to be looked up
     * @param apiKey Should be a valid FoodData Central API key.
     *
     * @return Returns a Kotlin Result object wrapping the [FoodSearchResultsList] object that
     *   represents the different possible foods in the USDA FoodData database that match the search
     *   query
     */

    suspend fun lookupFood(
        query: String?
    ): Result<FoodSearchResultsList?> = withContext(dispatcher) {
        try {
            val response = service.searchForFood(query)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
