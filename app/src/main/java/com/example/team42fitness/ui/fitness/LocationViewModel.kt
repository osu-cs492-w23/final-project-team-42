package com.example.team42fitness.ui.fitness

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Placeholder for LocationViewModel to mimic predefined viewmodels for now until I get things working.
 */
class LocationViewModel : ViewModel()
{
    private val _text = MutableLiveData<String>().apply {
        value = "This is Locations Fragment"
    }

    val text: LiveData<String> = _text
}