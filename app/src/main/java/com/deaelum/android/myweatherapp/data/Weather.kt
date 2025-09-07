package com.deaelum.android.myweatherapp.data

data class Weather(
    val cityName: String,
    val temperature: Double,
    val description: String,
    val humidity: Int,
    val tempMin: Double,
    val tempMax: Double
)
