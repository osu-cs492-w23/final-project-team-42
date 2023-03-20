package com.example.team42fitness.ui.food

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

    fun loadSearchResults(query: String){
        viewModelScope.launch {
            val result = repository.loadFoodSearch(query)
            _searchResults.value = result.getOrNull()
        }
    }
}