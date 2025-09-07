package com.deaelum.android.myweatherapp.data

import com.deaelum.android.myweatherapp.data.models.Clouds
import com.deaelum.android.myweatherapp.data.models.Coord
import com.deaelum.android.myweatherapp.data.models.Main
import com.deaelum.android.myweatherapp.data.models.Sys
import com.deaelum.android.myweatherapp.data.models.Weather
import com.deaelum.android.myweatherapp.data.models.Wind

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