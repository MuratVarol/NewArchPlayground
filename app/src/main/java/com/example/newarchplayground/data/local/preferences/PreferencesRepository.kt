package com.example.newarchplayground.data.local.preferences

import com.example.newarchplayground.di.UserPreference
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    @UserPreference private val preferencesDataSource: PreferencesDataSource
) {

    suspend fun getPrefName() = preferencesDataSource.getPreference(DataStorePreferenceConstants.NAME_KEY)

    suspend fun getPrefAge() = preferencesDataSource.getPreference(DataStorePreferenceConstants.INT_KEY)
}