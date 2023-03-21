package com.example.team42fitness.data.foodData

//import com.example.team42fitness.api.FoodDataSearchService
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class FoodSearchRepository(private val service: FoodDataSearchService, private val dispatcher: CoroutineDispatcher = Dispatchers.IO)
//{
//    suspend fun loadFoodSearch(query: String): Result<List<FoodItem>> = withContext(dispatcher){
//        try {
//            val response = service.loadFoodResults(query)
//            if (response.isSuccessful){
//                Result.success(response.body()?.foodsList ?: listOf())
//
//            }else{
//                Result.failure(Exception(response.errorBody()?.string()))
//            }
//        }catch (e: Exception){
//            Result.failure(e)
//        }
//    }
//}
