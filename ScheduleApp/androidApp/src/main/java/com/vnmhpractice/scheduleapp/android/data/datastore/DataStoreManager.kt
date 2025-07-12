package com.vnmhpractice.scheduleapp.android.data.datastore

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.vnmhpractice.scheduleapp.android.data.datasource.SettingsData
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager(val context: Context) {
    suspend fun saveSettings(settingsData: SettingsData) {
        context.dataStore.edit { preferences ->
            preferences[booleanPreferencesKey("darkTheme")] = settingsData.darkTheme
        }
    }

    fun getSettings() = context.dataStore.data.map { preferences ->
        return@map SettingsData(preferences[booleanPreferencesKey("darkTheme")] ?: true)
    }
}