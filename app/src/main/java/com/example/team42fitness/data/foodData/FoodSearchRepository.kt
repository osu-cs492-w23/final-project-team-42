package com.example.team42fitness.data.foodData

import android.util.Log
import com.example.team42fitness.api.FoodDataSearchService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodSearchRepository(private val service: FoodDataSearchService, private val dispatcher: CoroutineDispatcher = Dispatchers.IO)
{
    suspend fun loadFoodSearch(query: String): Result<List<FoodItem>> = withContext(dispatcher){
        try {
            val response = service.loadFoodResults(query)
            Log.d("FoodSearchService", "query: $query , response: $response ")
            if (response.isSuccessful){
                Log.d("FoodSearchService", "body: ${response.body()} , response: $response ")
                Result.success(response.body()?.foodsList ?: listOf())

            }else{
                Log.d("FoodSearchService", "body: ${response.errorBody()} , response: $response ")
                Result.failure(Exception(response.errorBody()?.string()))


            }
        }catch (e: Exception){
            Result.failure(e)

        }
    }


}