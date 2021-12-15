package com.example.newarchplayground.domain.usecase

import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.repository.PropertyRepositoryImp
import com.example.newarchplayground.data.util.Failure
import com.example.newarchplayground.ui.common.IUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPropertyListUseCase @Inject constructor(
    private val repo: PropertyRepositoryImp
) : IUseCase<List<PropertyUiModel>?> {

    override suspend fun invoke(): Flow<DataResult<Failure, List<PropertyUiModel>?>> {
        return repo.getProperties()
    }
}
