package com.example.newarchplayground.di

import android.content.Context
import com.example.newarchplayground.data.local.preferences.PreferencesDataSource
import com.example.newarchplayground.data.local.preferences.SETTINGS_PREFERENCES_NAME
import com.example.newarchplayground.data.local.preferences.USER_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class UserPreference

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SettingsPreference

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @UserPreference
    @Provides
    fun provideUserPreference(@ApplicationContext context: Context) =
        PreferencesDataSource(
            context = context,
            preferenceName = USER_PREFERENCES_NAME,
            migrationPreferenceName = USER_PREFERENCES_NAME
        )

    @SettingsPreference
    @Provides
    fun provideSettingsPreference(@ApplicationContext context: Context) =
        PreferencesDataSource(
            context = context,
            preferenceName = SETTINGS_PREFERENCES_NAME,
            migrationPreferenceName = SETTINGS_PREFERENCES_NAME
        )
}