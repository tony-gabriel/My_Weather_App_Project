package com.deaelum.android.myweatherapp.utils

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Splash : Screen("splash")
    data object WeatherDetails : Screen("weather")
}