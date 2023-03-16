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
 * This is a Retrofit service interface encapsulating communication with the OpenWeather API.
 */
interface FoodDataSearchService {
    /**
     * This method is used to query the OpenWeather API's 5-day/3-hour forecast method:
     * https://openweathermap.org/forecast5.  This is a suspending function, so it must be called
     * in a coroutine or within another suspending function.
     *
     * @param query Specifies the food to be looked up.
     * @param apiKey Should be a valid USDA FoodData Central API key.
     *
     * @return Returns a Retrofit `Response<>` object that will contain a [FiveDayForecast] object
     *   if the API call was successful.
     */
    @GET("forecast")
    suspend fun loadFiveDayForecast(
        @Query("query") query: String?,
        @Query("appid") apiKey: String
    ) : Response<FoodSearchResultsList>

    companion object {
        private const val BASE_URL = "https://api.nal.usda.gov/fdc/"

        /**
         * This method can be called as `OpenWeatherService.create()` to create an object
         * implementing the OpenWeatherService interface and which can be used to make calls to
         * the OpenWeather API.
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