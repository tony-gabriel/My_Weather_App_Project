package com.deaelum.android.myweatherapp.domain.model

data class Weather(
    val cityName: String,
    val temperature: Double,
    val description: String,
    val humidity: Int,
    val tempMin: Double,
    val tempMax: Double
)