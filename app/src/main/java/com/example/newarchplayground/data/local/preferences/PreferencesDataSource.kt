package com.example.newarchplayground.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.newarchplayground.data.common.ApiResult
import com.example.newarchplayground.data.repository.ApiResultFlowWrapperImpl
import com.example.newarchplayground.data.repository.IApiResultFlowWrapper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


const val USER_PREFERENCES_NAME = "user_preferences"
const val SETTINGS_PREFERENCES_NAME = "user_preferences2"

class PreferencesDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    preferenceName: String,
    migrationPreferenceName: String? = null,
) : IApiResultFlowWrapper by ApiResultFlowWrapperImpl() {

    private val Context.dataStore by preferencesDataStore(
        name = preferenceName,
        produceMigrations = { context ->
            // Since we're migrating from SharedPreferences, add a migration based on the
            if (migrationPreferenceName != null) {
                listOf(SharedPreferencesMigration(context, migrationPreferenceName))
            } else emptyList()
        }
    )

    suspend fun <T> getPreference(
        key: Preferences.Key<T>,
        defaultValue: T? = null
    ): Flow<ApiResult<T?>> = flow {
        emit(ApiResult.Loading)

        val dataFlow = context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                ApiResult.Success(preferences[key] ?: defaultValue)
            }

        emitAll(dataFlow)
    }


    suspend fun <T> editPreference(key: Preferences.Key<T>, value: T) = editorApiResultWrapper {
        context.dataStore.edit { it[key] = value }
    }

    suspend fun <T> removePreference(key: Preferences.Key<T>) = editorApiResultWrapper {
        context.dataStore.edit { it.remove(key) }
    }

    suspend fun clearAllPreference() = editorApiResultWrapper {
        context.dataStore.edit { it.clear() }
    }

    private suspend fun <T> editorApiResultWrapper(function: suspend () -> T): Flow<ApiResult<T>> =
        flowResult {
            try {
                ApiResult.Success(function.invoke())
            } catch (e: IOException) {
                Timber.e(e)
                ApiResult.Error(e.message ?: "", e)
            }
        }
}
