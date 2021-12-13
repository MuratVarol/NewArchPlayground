package com.example.newarchplayground.ui.common.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.example.newarchplayground.ui.common.UiState
import com.example.newarchplayground.ui.sample.StateHolder
import com.example.newarchplayground.util.lifecycleAwareState

@Composable
fun <STATE, T : StateHolder<STATE>> BaseComposeComponent(
    stateHolder: T,
    renderOnLoading: @Composable () -> Unit = {},
    renderOnFailure: @Composable () -> Unit = {},
    renderOnSuccess: @Composable (state: UiState<STATE>) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val state by lifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        stateFlow = stateHolder,
        initialState = UiState.Loading
    )

    when (state) {
        is UiState.Loading -> renderOnLoading()
        is UiState.Success -> renderOnSuccess(state)
        is UiState.Failure -> renderOnFailure()
    }
}
