package com.example.newarchplayground.domain.usecase

import androidx.compose.runtime.collectAsState
import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.repository.PropertyRepositoryImp
import com.example.newarchplayground.data.usecase.IUseCase
import com.example.newarchplayground.data.util.Failure
import com.example.newarchplayground.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetPropertyListUseCase @Inject constructor(
    private val repo: PropertyRepositoryImp
) : BaseUseCase<List<PropertyUiModel>?>() {

    override suspend fun invoke(): Flow<DataResult<Failure, List<PropertyUiModel>?>> = /*resultUiState {*/
        repo.getProperties()
//    }
}

abstract class BaseUseCase<T> : IUseCase<T> {

    suspend fun resultUiState(flow: suspend () -> Flow<DataResult<Failure, T>>): Flow<UiState<T>> {
        val a:Flow<UiState<T>> = flow().transform { data ->
            data.either(
                fnError = { handleError(it) },
                fnSuccess = { handleSuccess(it) },
                fnLoading = { handleLoading() }
            )
        }
        return a
    }

    private fun handleSuccess(it: T): UiState.Success<T> {
        return UiState.Success(it)
    }

    private fun handleLoading(): UiState.Loading {
        return UiState.Loading
    }

    private fun handleError(failure: Failure): UiState.Failure {
        return UiState.Failure(failure.localizedMessage ?: "")
    }
}
