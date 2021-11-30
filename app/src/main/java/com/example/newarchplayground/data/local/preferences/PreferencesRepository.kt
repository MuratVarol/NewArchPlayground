package com.example.newarchplayground.data.local.preferences

import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.util.Failure
import com.example.newarchplayground.di.SettingsPreference
import com.example.newarchplayground.di.UserPreference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    @UserPreference private val preferencesDataSource: PreferencesDataSource,
    @SettingsPreference private val settingsPreferenceDataSource: PreferencesDataSource,
) {

    fun getPrefName(): Flow<DataResult<Failure, String?>> =
        preferencesDataSource.getPreference(DataStorePreferenceConstants.NAME_KEY)

    fun hasOnboardingShown(): Flow<DataResult<Failure, Boolean?>> =
        settingsPreferenceDataSource.getPreference(DataStorePreferenceConstants.HAS_ONBOARDING_SHOWN_KEY)

    fun editName(value: String) =
        preferencesDataSource.editPreference(DataStorePreferenceConstants.NAME_KEY, value)
}