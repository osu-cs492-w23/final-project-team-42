package com.example.team42fitness.api

import com.example.team42fitness.data.foodData.FoodListJsonAdapter
import com.example.team42fitness.data.foodData.FoodSearchResultsList
import com.squareup.moshi.Moshi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface FoodDataSearchService {

    @GET("search")
    suspend fun loadFoodResults(
        @Query("query") query: String?,
        @Query("api_key") apiKey: String = "fzLMJqmkWeci3bkjhONuhFt4M9ZjGc6rwj1jCBfQ",
        @Query("pageSize") size: Int = 1
    ) : Response<FoodSearchResultsList>

    companion object {
        private const val BASE_URL = "https://api.nal.usda.gov/fdc/v1/foods/"


        fun create() : FoodDataSearchService {
            val moshi = Moshi.Builder()
                .add(FoodListJsonAdapter())
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(FoodDataSearchService::class.java)
        }
    }
}