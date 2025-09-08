package com.deaelum.android.myweatherapp.di

import android.content.Context
import com.deaelum.android.myweatherapp.data.localStorage.PreferencesManager
import com.deaelum.android.myweatherapp.domain.repository.WeatherRepository
import com.deaelum.android.myweatherapp.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepo(weatherRepoImpl: WeatherRepositoryImpl): WeatherRepository

    companion object{
        @Provides
        @Singleton
        fun providePrefManager(@ApplicationContext context: Context): PreferencesManager = PreferencesManager(context)
    }

}