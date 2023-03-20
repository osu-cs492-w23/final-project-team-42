package com.example.team42fitness.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FitnessActivityDao {
    @Insert
    suspend fun insert(activity: FitnessActivity)

    @Delete
    suspend fun delete(activity: FitnessActivity)

    @Query("SELECT * FROM FitnessActivity")
    fun getAllActivities(): Flow<List<FitnessActivity>>

    @Query("SELECT SUM(caloriesBurned) / 7 " +
           "FROM FitnessActivity " +
           "GROUP BY WEEK(date) " +
           "HAVING WEEK(date) == :weekOffset")
    fun getAverageDailyCaloriesByWeek(weekOffset: Int): Flow<Int>

    @Query("SELECT COUNT(id) / 7 " +
            "FROM FitnessActivity " +
            "GROUP BY WEEK(date) " +
            "HAVING WEEK(date) == :weekOffset")
    fun getActivityCountByWeek(weekOffset: Int): Flow<Int>
}