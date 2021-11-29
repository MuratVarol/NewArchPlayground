package com.example.newarchplayground.di

import com.example.newarchplayground.data.repository.PropertyRepositoryImp
import com.example.newarchplayground.data.repository.SomeOtherRepositoryImp
import com.example.newarchplayground.domain.usecase.GetPropertyListUseCase
import com.example.newarchplayground.domain.usecase.GetSomeOtherUseCase
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
    fun provideGetCharacterListUseCase(repo: PropertyRepositoryImp) = GetPropertyListUseCase(repo)

    @Provides
    @ViewModelScoped
    fun provideGetSomeValuesUseCase(repo:SomeOtherRepositoryImp) = GetSomeOtherUseCase(repo)
}
