package com.example.team42fitness.ui.food

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.team42fitness.data.AppDatabase
import com.example.team42fitness.data.food.Food
import com.example.team42fitness.data.food.FoodRepository
import kotlinx.coroutines.launch

class NutritionViewModel(application: Application): AndroidViewModel(application) {

    private val repository = FoodRepository(AppDatabase.getInstance(application).foodDao())

    fun addFoodItem(food: Food){
        viewModelScope.launch {
            repository.insertFood(food)
        }
    }

    val allFoodItems = repository.getAllFoods().asLiveData()

//    fun getAllFoodItems(){
//        viewModelScope.launch {
//            repository.getAllFoods()
//        }
//    }
}