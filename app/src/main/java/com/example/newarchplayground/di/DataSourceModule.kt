package com.example.newarchplayground.di

import com.example.newarchplayground.data.remote.PropertyApi
import com.example.newarchplayground.data.remote.SomeOtherApi
import com.example.newarchplayground.data.remote.datasource.PropertyRemoteDataSource
import com.example.newarchplayground.data.remote.datasource.SomeOtherRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providePropertyRemoteDataSource(api: PropertyApi): PropertyRemoteDataSource =
        PropertyRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideSomeOtherRemoteDataSource(api: SomeOtherApi): SomeOtherRemoteDataSource =
        SomeOtherRemoteDataSource(api)
}
