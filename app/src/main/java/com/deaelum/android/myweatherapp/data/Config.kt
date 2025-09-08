package com.deaelum.android.myweatherapp.data

object Config {
    const val BASE_URL = ""
    const val WEATHER_ICON_URL = ""

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