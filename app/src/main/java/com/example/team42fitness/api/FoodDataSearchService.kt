package com.example.team42fitness.api

import com.example.team42fitness.data.FoodListJsonAdapter
import com.example.team42fitness.data.FoodSearchResultsList
import com.squareup.moshi.Moshi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * This is a Retrofit service interface encapsulating communication with the FoodDataCentral API.
 */
interface FoodDataSearchService {
    /**
     * This method is used to query the USDA FoodDataCentral API's food search method:
     * https://api.nal.usda.gov/fdc/v1/foods/search.  This is a suspending function, so it must be
     * called in a coroutine or within another suspending function.
     *
     * @param foodQuery Specifies the food to be looked up.
     * @param apiKey Should be a valid USDA FoodData Central API key.
     *
     * @return Returns a Retrofit `Response<>` object that will contain a [FoodSearchResultsList]
     * object if the API call was successful.
     */
    @GET("foods/search")
    suspend fun searchForFood(
        @Query("query") foodQuery: String = "Cheese",
        @Query("appid") apiKey: String
    ) : Response<FoodSearchResultsList>

    companion object {
        private const val BASE_URL = "https://api.nal.usda.gov/fdc/v1/"

        /**
         * This method can be cal led as `FoodDataSearchService.create()` to create an object
         * implementing the FoodData central interface and which can be used to make calls to
         * the FoodData Central API.
         */
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