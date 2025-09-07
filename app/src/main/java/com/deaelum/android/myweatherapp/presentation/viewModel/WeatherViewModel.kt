package com.deaelum.android.myweatherapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deaelum.android.myweatherapp.data.Weather
import com.deaelum.android.myweatherapp.networkComponents.Resources
import com.deaelum.android.myweatherapp.repositories.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WeatherState(
    val weather: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class WeatherViewModel @Inject constructor(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {
    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            getWeatherUseCase(cityName).collect { resources ->
                when(resources){
                    is Resources.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                    is Resources.Success -> {
                        _state.value = _state.value.copy(weather = resources.data, isLoading = false)
                    }

                    is Resources.Error<*> -> {
                        _state.value = _state.value.copy(error = resources.message, isLoading = false)

                    }
                }
            }
        }
    }

}