package com.example.team42fitness.ui.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team42fitness.api.FoodDataSearchService
import com.example.team42fitness.api.food.FoodDataSearchRepository
import com.example.team42fitness.api.food.FoodSearchResultsList
import com.example.team42fitness.api.food.LoadingStatus
import kotlinx.coroutines.launch

class FoodSearchViewModel: ViewModel() {
    private val repository = FoodDataSearchRepository(FoodDataSearchService.create())
    private val _searchResults = MutableLiveData<FoodSearchResultsList?>(null)
    val searchResults: LiveData<FoodSearchResultsList?> = _searchResults

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _loadingStatus = MutableLiveData<LoadingStatus>(LoadingStatus.SUCCESS)
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

    fun loadSearchResults(query: String){
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.LOADING
            val result = repository.lookupFood(query)
            // get that result and store in repo
            _searchResults.value = result.getOrNull()
            _errorMessage.value = result.exceptionOrNull()?.message
            _loadingStatus.value = when(result.isSuccess){
                true -> LoadingStatus.SUCCESS
                false -> LoadingStatus.ERROR
            }
        }
    }
}
