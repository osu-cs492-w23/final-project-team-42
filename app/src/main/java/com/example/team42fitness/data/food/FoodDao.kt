package com.example.team42fitness.data.food

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
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