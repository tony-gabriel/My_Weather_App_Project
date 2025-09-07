package com.deaelum.android.myweatherapp.repositories

import javax.inject.Inject

class GetFavouriteCityUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(): String? {
        return repository.getFavouriteCity()
    }
}