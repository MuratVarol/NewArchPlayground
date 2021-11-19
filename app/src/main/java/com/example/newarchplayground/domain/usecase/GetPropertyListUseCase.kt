package com.example.newarchplayground.domain.usecase

import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.data.remote.PropertyRepository
import kotlinx.coroutines.flow.Flow

class GetPropertyListUseCase(private val repo: PropertyRepository) {
    fun getProperties(): Flow<List<PropertyUiModel>> = repo.getCharacterList()
}