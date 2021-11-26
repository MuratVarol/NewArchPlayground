package com.example.newarchplayground.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


const val USER_PREFERENCES_NAME = "user_preferences"
const val SETTINGS_PREFERENCES_NAME = "user_preferences2"

class PreferencesDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    preferenceName: String,
    migrationPreferenceName: String? = null,
) {
    private val Context.dataStore by preferencesDataStore(
        name = preferenceName,
        produceMigrations = { context ->
            // Since we're migrating from SharedPreferences, add a migration based on the
            if (migrationPreferenceName != null) {
                listOf(SharedPreferencesMigration(context, migrationPreferenceName))
            } else emptyList()
        }
    )

    suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T? = null): Flow<T?> =
        context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[key] ?: defaultValue
        }

    suspend fun <T> editPreference(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { it[key] = value }
    }

    suspend fun <T> removePreference(key: Preferences.Key<T>) {
        context.dataStore.edit { it.remove(key) }
    }

    suspend fun clearAllPreference() {
        context.dataStore.edit { it.clear() }
    }
}