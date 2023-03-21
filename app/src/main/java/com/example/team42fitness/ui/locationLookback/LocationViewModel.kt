package com.example.team42fitness.ui.locationLookback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Placeholder for LocationViewModel to mimic predefined ViewModels for now until I have to edit if need be.
 */
class LocationViewModel : ViewModel()
{
    private val _text = MutableLiveData<String>().apply {
        value = "This is Location Lookback Fragment"
    }

    val text: LiveData<String> = _text
}