package com.example.newarchplayground.data.local.preferences

import com.example.newarchplayground.di.SettingsPreference
import com.example.newarchplayground.di.UserPreference
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    @UserPreference private val preferencesDataSource: PreferencesDataSource,
    @SettingsPreference private val settingsPreferenceDataSource: PreferencesDataSource,
) {

    fun getPrefName() =
        preferencesDataSource.getPreference(DataStorePreferenceConstants.NAME_KEY)

    fun hasOnboardingShown() =
        settingsPreferenceDataSource.getPreference(DataStorePreferenceConstants.HAS_ONBOARDING_SHOWN_KEY)

    fun editName(value: String) =
        preferencesDataSource.editPreference(DataStorePreferenceConstants.NAME_KEY, value)
}