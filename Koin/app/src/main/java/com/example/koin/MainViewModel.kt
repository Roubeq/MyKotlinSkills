package com.example.koin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin.models.CountriesItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel(
    private val repository: MyRepository
) : ViewModel() {
    private val _coutries = MutableStateFlow<List<CountriesItem>>(emptyList())
    val countries: StateFlow<List<CountriesItem>> = _coutries

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            try {
                _coutries.value = repository.fetchCountries()
            } catch(e: Exception) {
                Log.d("fetchCountries", e.message.toString() )
            }
        }
    }
}