package com.deaelum.android.myweatherapp.utils

object Config {
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    fun getWeatherIcon(description: String): String {
        return when {
            description.contains("clear", ignoreCase = true) -> "☀️"
            description.contains("cloud", ignoreCase = true) -> "☁️"
            description.contains("rain", ignoreCase = true) -> "🌧️"
            description.contains("storm", ignoreCase = true) -> "⛈️"
            description.contains("snow", ignoreCase = true) -> "❄️"
            description.contains("mist", ignoreCase = true) -> "🌫️"
            description.contains("fog", ignoreCase = true) -> "🌫️"
            else -> "🌤️"
        }
    }
}