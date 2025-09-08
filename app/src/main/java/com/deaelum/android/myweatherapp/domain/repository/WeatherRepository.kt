package com.deaelum.android.myweatherapp.domain.repository

import com.deaelum.android.myweatherapp.domain.model.Weather
import com.deaelum.android.myweatherapp.domain.networkComponents.Resources
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(cityName: String): Flow<Resources<Weather>>
    suspend fun saveFavouriteCity(cityName: String)
    suspend fun getFavouriteCity(): String?
}