package com.deaelum.android.myweatherapp.repositories

import com.deaelum.android.myweatherapp.data.Weather
import com.deaelum.android.myweatherapp.networkComponents.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityName: String): Flow<Resources<Weather>> {
        if (cityName.isBlank()){
            return flow { emit(Resources.Error("City name cannot be empty")) }
        }
        return repository.getWeather(cityName.trim())
    }
}