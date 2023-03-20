package com.example.team42fitness.data

class FitnessActivitiesRepository(private val dao: FitnessActivityDao) {
    suspend fun insertFitnessActivity(activity: FitnessActivity) = dao.insert(activity)
    suspend fun deleteFitnessActivity(activity: FitnessActivity) = dao.delete(activity)

    fun getAllActivities(activity: FitnessActivity) = dao.getAllActivities()
    fun getAverageDailyCaloriesByWeek(weekOffset: Int) = dao.getAverageDailyCaloriesByWeek(weekOffset)
    fun getActivityCountByWeek(weekOffset: Int) = dao.getActivityCountByWeek(weekOffset)
}