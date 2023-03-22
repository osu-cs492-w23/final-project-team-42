package com.example.team42fitness.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.team42fitness.data.AppDatabase
import com.example.team42fitness.data.activity.FitnessActivitiesRepository
import com.example.team42fitness.data.activity.FitnessActivity
import com.example.team42fitness.data.food.Food
import com.example.team42fitness.data.food.FoodRepository
import kotlinx.coroutines.launch

class FoodViewModel(application: Application): AndroidViewModel(application) {
    private val repository = FoodRepository(
        AppDatabase.getInstance(application).foodDao()
    )

    val foods = repository.getAllFoods().asLiveData()

    fun addFood(food: Food) {
        viewModelScope.launch {
            repository.insertFood(food)
        }
    }

    fun removeFood(food: Food) {
        viewModelScope.launch {
            repository.deleteFood(food)
        }
    }

    fun getFoodsByDate(date: String) =
        repository.getFoodsByDate(date).asLiveData()

    fun getFoodsByFdcid(fdcid: String) =
        repository.getFoodsByFdcid(fdcid).asLiveData()
}