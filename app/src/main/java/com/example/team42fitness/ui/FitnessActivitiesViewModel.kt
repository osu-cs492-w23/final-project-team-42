package com.example.team42fitness.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.team42fitness.data.AppDatabase
import com.example.team42fitness.data.FitnessActivitiesRepository
import com.example.team42fitness.data.FitnessActivity
import kotlinx.coroutines.launch

class FitnessActivitiesViewModel(application: Application): AndroidViewModel(application) {
    private val repository = FitnessActivitiesRepository(
        AppDatabase.getInstance(application).fitnessActivityDao()
    )

    val fitnessActivities = repository.getAllActivities().asLiveData()

    fun addFitnessActivity(activity: FitnessActivity) {
        viewModelScope.launch {
            repository.insertFitnessActivity(activity)
        }
    }

    fun removeFitnessActivity(activity: FitnessActivity) {
        viewModelScope.launch {
            repository.deleteFitnessActivity(activity)
        }
    }

    fun getAverageDailyCaloriesByWeek(weekOffset: Int) {
        viewModelScope.launch {
            repository.getAverageDailyCaloriesByWeek(weekOffset)
        }
    }

    fun getActivityCountByWeek(weekOffset: Int) {
        viewModelScope.launch {
            repository.getActivityCountByWeek(weekOffset)
        }
    }
}