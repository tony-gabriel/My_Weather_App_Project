package com.deaelum.android.myweatherapp.repositories

import javax.inject.Inject

class SaveFavouriteCityUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityName: String) {
        if (cityName.isNotBlank()) {
            repository.saveFavouriteCity(cityName)
        }
    }

}