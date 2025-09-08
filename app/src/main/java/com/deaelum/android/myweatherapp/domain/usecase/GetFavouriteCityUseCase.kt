package com.deaelum.android.myweatherapp.domain.usecase

import com.deaelum.android.myweatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetFavouriteCityUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(): String? {
        return repository.getFavouriteCity()
    }
}