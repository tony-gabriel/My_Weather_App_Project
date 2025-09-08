package com.deaelum.android.myweatherapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deaelum.android.myweatherapp.repositories.GetFavouriteCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val cityName: String = "",
    val isLoading: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFavoriteCityUseCase: GetFavouriteCityUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        loadFavCity()
    }

    fun onCityNameChanged(cityName: String) {
        _state.value = _state.value.copy(cityName = cityName)

    }

    private fun loadFavCity() {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            try {
                val cityName = getFavoriteCityUseCase()
                _state.value = _state.value.copy(
                    cityName = cityName ?: "",
                    isLoading = false
                )
            }catch (e: Exception){
                _state.value = _state.value.copy(isLoading = false)
                Log.e("HomeViewModel", "Error loading favorite city, ${e.localizedMessage}", e)
            }

        }
    }
}