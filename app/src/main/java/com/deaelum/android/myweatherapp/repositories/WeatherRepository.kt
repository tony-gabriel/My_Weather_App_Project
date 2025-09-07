package com.deaelum.android.myweatherapp.repositories

import com.deaelum.android.myweatherapp.data.Weather
import com.deaelum.android.myweatherapp.networkComponents.Resources
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(cityName: String): Flow<Resources<Weather>>
    suspend fun saveFavouriteCity(cityName: String)
    suspend fun getFavouriteCity(): String?
}