package com.example.newarchplayground.domain.usecase

import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.data.common.ApiResult
import com.example.newarchplayground.data.repository.PropertyRepositoryImp
import com.example.newarchplayground.data.util.Failure
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPropertyListUseCase @Inject constructor(private val repo: PropertyRepositoryImp) {
    fun getProperties(): Flow<ApiResult<Failure, List<PropertyUiModel>?>> = repo.getProperties()
}