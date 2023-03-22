package com.example.team42fitness.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to 42 Fitness!\n\n Track your exercise and dietary habits with ease. Just " +
                "select an option from the dropdown menu to get started!"
    }
    val text: LiveData<String> = _text
}