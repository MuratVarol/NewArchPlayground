package com.example.newarchplayground.util.extensions

import com.example.newarchplayground.data.common.ResultAlias
import com.example.newarchplayground.ui.common.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <DATA, RESULT> Flow<ResultAlias<DATA>>.mapUiState(
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