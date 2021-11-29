package com.example.newarchplayground.di

import com.example.newarchplayground.data.PropertyRepository
import com.example.newarchplayground.data.remote.datasource.PropertyRemoteDataSource
import com.example.newarchplayground.data.repository.PropertyRepositoryImp
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
    fun providePropertyRepository(dataSource: PropertyRemoteDataSource): PropertyRepository =
        PropertyRepositoryImp(dataSource)
}
