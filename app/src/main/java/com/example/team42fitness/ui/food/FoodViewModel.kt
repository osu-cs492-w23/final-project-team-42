package com.example.team42fitness.ui.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodViewModel : ViewModel() {
  private val _text = MutableLiveData<String>().apply {
        value = "This is Food Fragment"
    }
    val text: LiveData<String> = _text


}