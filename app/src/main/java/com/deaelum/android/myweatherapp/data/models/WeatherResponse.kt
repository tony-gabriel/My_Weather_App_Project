package com.deaelum.android.myweatherapp.data.models

import com.deaelum.android.myweatherapp.domain.model.Clouds
import com.deaelum.android.myweatherapp.domain.model.Coord
import com.deaelum.android.myweatherapp.domain.model.Main
import com.deaelum.android.myweatherapp.domain.model.Sys
import com.deaelum.android.myweatherapp.domain.model.Wind

data class WeatherResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)