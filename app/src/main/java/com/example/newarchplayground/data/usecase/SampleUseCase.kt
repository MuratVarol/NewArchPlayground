package com.example.newarchplayground.data.usecase

import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.util.Failure
import com.example.newarchplayground.ui.common.UiState
import com.example.newarchplayground.ui.sample.SampleUIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.random.Random

typealias ResultAlias<T> = DataResult<Failure, T>

class SampleUseCase @Inject constructor() :
    IUiStateUseCase<List<String>, SampleUIState> {

    override suspend fun invoke(
        scope: CoroutineScope,
        stateCallback: (UiState<SampleUIState>) -> Unit
    ) {
        flow<ResultAlias<List<String>>> {
            emit(DataResult.Loading)
            val list = mutableListOf<String>().apply {
                repeat(4) {
                    add(Random.nextInt(0, 5).toString())
                }
            }
            emit(DataResult.Success(list))
        }.mapUiState(scope, stateCallback, mapOnSuccess = {
            SampleUIState(list = it)
        })
    }
}

interface IUseCase<out T> {
    suspend operator fun invoke(): Flow<ResultAlias<T>>
}

interface IUiStateUseCase<DATA, RESULT> {
    suspend operator fun invoke(scope: CoroutineScope, stateCallback: (UiState<RESULT>) -> Unit)

    fun Flow<ResultAlias<DATA>>.mapUiState(
        scope: CoroutineScope,
        stateCallback: (UiState<RESULT>) -> Unit,
        mapOnSuccess: (DATA) -> RESULT
    ) {
        onEach { data ->
            val uiState = data.either(
                fnError = { UiState.Failure(it.localizedMessage ?: "") },
                fnSuccess = { UiState.Success(mapOnSuccess(it)) },
                fnLoading = { UiState.Loading }
            )
            stateCallback(uiState)
        }.launchIn(scope)
    }
}