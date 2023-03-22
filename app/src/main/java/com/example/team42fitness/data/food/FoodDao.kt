package com.example.team42fitness.data.food

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Insert
    suspend fun insert(food: Food)

    @Delete
    suspend fun delete(food: Food)

    @Query("SELECT * FROM Food")
    fun getAllFoods(): Flow<List<Food>>

    @Query("SELECT * FROM Food WHERE date = :date")
    fun getFoodsByDate(date: String): Flow<List<Food>>

    @Query("SELECT * FROM Food WHERE fdcid = :fdcid")
    fun getFoodsByFdcid(fdcid: String): Flow<List<Food>>
}