package com.example.newarchplayground.ui.delegate.mapper

import com.example.newarchplayground.data.usecase.ResultAlias
import com.example.newarchplayground.ui.common.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface IUiStateMap {

    fun <DATA, RESULT> Flow<ResultAlias<DATA>>.mapUiState(
        scope: CoroutineScope,
        stateCallback: (UiState<RESULT>) -> Unit,
        mapOnSuccess: (DATA) -> RESULT
    )
}