package com.example.team42fitness.data.food

class FoodRepository(private val dao: FoodDao) {
    suspend fun insertFood(food: Food) = dao.insert(food)
    suspend fun deleteFood(food: Food) = dao.delete(food)

    fun getAllFoods() = dao.getAllFoods()
    fun getFoodsByDate(date: String) = dao.getFoodsByDate(date)
    fun getFoodsByFdcid(fdcid: String) = dao.getFoodsByFdcid(fdcid)
}