package com.deaelum.android.myweatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.deaelum.android.myweatherapp.domain.model.Weather
import com.deaelum.android.myweatherapp.domain.networkComponents.Resources
import com.deaelum.android.myweatherapp.domain.usecase.GetWeatherUseCase
import com.deaelum.android.myweatherapp.domain.usecase.SaveFavouriteCityUseCase
import com.deaelum.android.myweatherapp.presentation.viewModel.WeatherViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getWeatherUseCase: GetWeatherUseCase

    @Mock
    private lateinit var saveFavoriteCityUseCase: SaveFavouriteCityUseCase

    private lateinit var viewModel: WeatherViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = WeatherViewModel(getWeatherUseCase, saveFavoriteCityUseCase)
    }

    @Test
    fun `when weather data received, success state is shown`() = runTest {
        // Given
        val weather = Weather(
            cityName = "London",
            temperature = 20.0,
            description = "Sunny",
            humidity = 60,
            tempMin = 18.0,
            tempMax = 25.0
        )
        whenever(getWeatherUseCase.invoke("London")).thenReturn(
            flowOf(Resources.Success(weather))
        )

        // When
        viewModel.getWeather("London")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assert(viewModel.state.value.weather == weather)
        assert(!viewModel.state.value.isLoading)
        assert(viewModel.state.value.error == null)
    }

    @Test
    fun `when weather fetch fails, error state is shown`() = runTest {
        // Given
        val errorMessage = "City not found"
        whenever(getWeatherUseCase.invoke("InvalidCity")).thenReturn(
            flowOf(Resources.Error(errorMessage))
        )

        // When
        viewModel.getWeather("InvalidCity")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assert(viewModel.state.value.error == errorMessage)
        assert(!viewModel.state.value.isLoading)
        assert(viewModel.state.value.weather == null)
    }

    @Test
    fun `when favIcon clicked, favorite city is saved`() = runTest {
        // When
        viewModel.onFavIconClick("Paris")
        testDispatcher.scheduler.advanceUntilIdle() // Added this line

        // Then
        verify(saveFavoriteCityUseCase).invoke("Paris")
    }

}