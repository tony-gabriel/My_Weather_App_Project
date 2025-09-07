package com.deaelum.android.myweatherapp.di

import android.content.Context
import com.deaelum.android.myweatherapp.localStorage.PreferencesManager
import com.deaelum.android.myweatherapp.repositories.WeatherRepository
import com.deaelum.android.myweatherapp.repositories.WeatherRepositoryImpl
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