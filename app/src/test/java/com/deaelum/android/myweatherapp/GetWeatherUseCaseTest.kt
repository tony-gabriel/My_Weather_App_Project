package com.deaelum.android.myweatherapp

import com.deaelum.android.myweatherapp.domain.model.Weather
import com.deaelum.android.myweatherapp.domain.networkComponents.Resources
import com.deaelum.android.myweatherapp.domain.repository.WeatherRepository
import com.deaelum.android.myweatherapp.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class GetWeatherUseCaseTest {

    @Mock
    private lateinit var repository: WeatherRepository

    private lateinit var useCase: GetWeatherUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = GetWeatherUseCase(repository)
    }

    @Test
    fun `when city name is empty, error is returned`() = runTest {
        // When
        val result = useCase.invoke("").first()

        // Then
        assert(result is Resources.Error)
        assert((result as Resources.Error).message == "City name cannot be empty")
    }

    @Test
    fun `when city name is blank, error is returned`() = runTest {
        // When
        val result = useCase.invoke("   ").first()

        // Then
        assert(result is Resources.Error)
        assert((result as Resources.Error).message == "City name cannot be empty")
    }

    @Test
    fun `when city name is valid, repository is called with trimmed name`() = runTest {
        // Given
        val weather = Weather(
            cityName = "London",
            temperature = 20.0,
            description = "Sunny",
            humidity = 60,
            tempMin = 18.0,
            tempMax = 25.0
        )
        whenever(repository.getWeather("London")).thenReturn(
            flowOf(Resources.Success(weather))
        )

        // When
        val result = useCase.invoke("  London  ").first()

        // Then
        assert(result is Resources.Success)
        assert((result as Resources.Success).data == weather)
    }
}