package com.example.newarchplayground.ui.composable

import com.example.newarchplayground.ui.common.BaseViewModel
import com.example.newarchplayground.ui.common.UiState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner

@Composable
fun <STATE, VM : BaseViewModel<STATE>> BaseComposeScreen(
    scaffoldState: ScaffoldState,
    viewModel: VM,
    renderOnLoading: @Composable (loadingHandler: (Boolean) -> Unit) -> Unit = {
        com.example.newarchplayground.ui.common.LoadingScreen()
    },
    renderOnFailure: @Composable (failureHandler: (String) -> Unit) -> Unit = {
        com.example.newarchplayground.ui.common.FailureScreen()
    },
    renderOnSuccess: @Composable (state: UiState<STATE>) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    com.example.newarchplayground.ui.common.HandleSnackBarIfSupported(
        lifecycleOwner,
        viewModel,
        scaffoldState
    )

    val state by com.example.newarchplayground.util.lifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        stateFlow = viewModel.uiState,
        initialState = viewModel.initialState
    )

    when (state) {
        is UiState.Loading -> renderOnLoading.invoke(viewModel.loadingHandler)
        is UiState.Success -> renderOnSuccess.invoke(state)
        is UiState.Failure -> renderOnFailure.invoke(viewModel.failureHandler)
    }
}

@Composable
fun LoadingScreen() {
    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Loading",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun FailureScreen() {
    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Error occurred",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}