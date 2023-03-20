package com.example.team42fitness.ui.food

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team42fitness.api.FoodDataSearchService
import com.example.team42fitness.data.foodData.FoodItem
import com.example.team42fitness.data.foodData.FoodSearchRepository
import kotlinx.coroutines.launch

class FoodSearchViewModel: ViewModel() {
    private val repository = FoodSearchRepository(FoodDataSearchService.create())
    private val _searchResults = MutableLiveData<List<FoodItem>?>(null)
    val searchResults: LiveData<List<FoodItem>?> = _searchResults

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    fun loadSearchResults(query: String){
        Log.d("FoodSearchViewModel","query: $query")
        viewModelScope.launch {
            val result = repository.loadFoodSearch(query)
            Log.d("FoodSearchViewModel","result: $result")

            // get that result and store in repo
            _searchResults.value = result.getOrNull()
            _errorMessage.value = result.exceptionOrNull()?.message
        }
    }
}