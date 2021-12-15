package com.example.newarchplayground.domain.usecase

import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.repository.PropertyRepositoryImp
import com.example.newarchplayground.data.util.Failure
import com.example.newarchplayground.ui.common.UiState
import com.example.newarchplayground.ui.propertylist.MainScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetPropertyListUseCase @Inject constructor(
    private val repo: PropertyRepositoryImp
) : IUseCase2<List<PropertyUiModel>?> {

    override suspend fun invoke(): Flow<DataResult<Failure, List<PropertyUiModel>?>> {
        return repo.getProperties()
    }
}

interface IUseCase2<DATA> {
    suspend operator fun invoke(): Flow<DataResult<Failure, DATA>>
}