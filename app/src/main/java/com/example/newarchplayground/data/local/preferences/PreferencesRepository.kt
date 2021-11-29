package com.example.newarchplayground.data.local.preferences

import com.example.newarchplayground.di.SettingsPreference
import com.example.newarchplayground.di.UserPreference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    @UserPreference private val preferencesDataSource: PreferencesDataSource,
    @SettingsPreference private val settingsPreferenceDataSource: PreferencesDataSource,
) {

    suspend fun getPrefName() =
        preferencesDataSource.getPreference(DataStorePreferenceConstants.NAME_KEY)

    suspend fun hasOnboardingShown(): Flow<Boolean?> =
        settingsPreferenceDataSource.getPreference(DataStorePreferenceConstants.HAS_ONBOARDING_SHOWN_KEY)
}