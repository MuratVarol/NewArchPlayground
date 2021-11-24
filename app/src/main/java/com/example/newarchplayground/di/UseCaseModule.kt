package com.example.newarchplayground.di

import com.example.newarchplayground.data.repository.PropertyRepository
import com.example.newarchplayground.domain.usecase.GetPropertyListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetCharacterListUseCase(repo: PropertyRepository) = GetPropertyListUseCase(repo)
}
