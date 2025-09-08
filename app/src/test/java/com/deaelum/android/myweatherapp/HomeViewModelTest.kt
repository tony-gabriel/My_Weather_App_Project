package com.deaelum.android.myweatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.deaelum.android.myweatherapp.domain.usecase.GetFavouriteCityUseCase
import com.deaelum.android.myweatherapp.presentation.viewModel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getFavoriteCityUseCase: GetFavouriteCityUseCase

    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(getFavoriteCityUseCase)
    }

    @Test
    fun `when city name changed, state is updated`() {
        // When
        viewModel.onCityNameChanged("London")

        // Then
        assert(viewModel.state.value.cityName == "London")
    }

    @Test
    fun `when favorite city exists, it loads on init`() = runTest {
        // Given
        whenever(getFavoriteCityUseCase.invoke()).thenReturn("Tokyo")

        // When
        viewModel = HomeViewModel(getFavoriteCityUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assert(viewModel.state.value.cityName == "Tokyo")
    }
}