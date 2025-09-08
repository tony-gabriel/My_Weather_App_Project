package com.deaelum.android.myweatherapp.data.api

import com.deaelum.android.myweatherapp.data.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
    ): Response<WeatherResponse>

}