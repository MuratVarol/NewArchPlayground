package com.example.newarchplayground.di

import com.example.newarchplayground.data.remote.PropertyApi
import com.example.newarchplayground.data.remote.PropertyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(api: PropertyApi): PropertyRepository =
        PropertyRepository(api)
}
