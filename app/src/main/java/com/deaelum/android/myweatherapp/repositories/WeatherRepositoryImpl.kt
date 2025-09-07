package com.deaelum.android.myweatherapp.repositories

import com.deaelum.android.myweatherapp.data.Weather
import com.deaelum.android.myweatherapp.data.WeatherResponse
import com.deaelum.android.myweatherapp.localStorage.PreferencesManager
import com.deaelum.android.myweatherapp.networkComponents.Resources
import com.deaelum.android.myweatherapp.networkComponents.WeatherApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
    private val preferencesManager: PreferencesManager,
    private val apiKey: String
): WeatherRepository {
    override suspend fun getWeather(cityName: String): Flow<Resources<Weather>> = flow {
        try {
            emit(Resources.Loading())
            val response = apiService.getWeather(cityName = cityName, apiKey = apiKey)

            if (response.isSuccessful){
                response.body()?.let {
                    val weather = it.toWeather()
                    emit(Resources.Success(weather))
                }
            }else{
                emit(Resources.Error("No City found"))
            }

        }catch (e: HttpException){
            emit(Resources.Error("Network Error: ${e.localizedMessage}"))
        }catch (e: IOException){
            emit(Resources.Error("Check your internet connection"))
        }catch(e: Exception){
            emit(Resources.Error("An unexpected error occurred"))
        }
    }

    override suspend fun saveFavouriteCity(cityName: String) {
        preferencesManager.saveFavCity(cityName)
    }

    override suspend fun getFavouriteCity(): String? {
        return preferencesManager.getFavCity()
    }

    private fun WeatherResponse.toWeather(): Weather {
        return Weather(
            cityName = name,
            temperature = main.temp,
            description = weather.firstOrNull()?.description?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            } ?: "Unknown",
            humidity = main.humidity,
            tempMin = main.temp_min,
            tempMax = main.temp_max
        )
    }

}