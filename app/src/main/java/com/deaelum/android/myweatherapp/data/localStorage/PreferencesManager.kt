package com.deaelum.android.myweatherapp.data.localStorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("weather_preferences")

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val faveCityKey = stringPreferencesKey("faveCity")
    private val dataStore = context.dataStore

    suspend fun saveFavCity(cityName: String){
        dataStore.edit { preferences ->
            preferences[faveCityKey] = cityName
        }
    }

    suspend fun getFavCity(): String?{
        return dataStore.data.map { preferences ->
            preferences[faveCityKey]
        }.first()
    }
}