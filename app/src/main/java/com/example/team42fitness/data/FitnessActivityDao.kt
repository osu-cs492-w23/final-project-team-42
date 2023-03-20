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

    @Query("SELECT" +
           "    SUM(caloriesBurned) /" +
           "        CASE " +
           "            WHEN :weekOffset > 0 THEN 7 " +
           "            ELSE 1 + cast(strftime('%w', date) as int) " +
           "        END " +
           "FROM FitnessActivity " +
           "GROUP BY strftime('%W', date) " +
           "HAVING cast(strftime('%W', date) as int) + :weekOffset = " +
           "    cast(strftime('%W', DATE('now')) as int)")
    fun getAverageDailyCaloriesByWeek(weekOffset: Int): Flow<Int>

    @Query("SELECT COUNT(id) " +
            "FROM FitnessActivity " +
            "GROUP BY strftime('%W', date) " +
            "HAVING cast(strftime('%W', date) as int) + :weekOffset = " +
            "    cast(strftime('%W', DATE('now')) as int)")
    fun getActivityCountByWeek(weekOffset: Int): Flow<Int>
}