package com.example.newarchplayground.ui.common.composable

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
import com.example.newarchplayground.ui.common.BaseSateViewModel
import com.example.newarchplayground.ui.common.UiState
import com.example.newarchplayground.ui.sample.BaseViewModel
import com.example.newarchplayground.util.lifecycleAwareState

@Composable
fun <STATE, VM : BaseViewModel<STATE>> BaseComposeScreen(
    scaffoldState: ScaffoldState,
    viewModel: VM,
    render: @Composable () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    HandleSnackBarIfSupported(lifecycleOwner, viewModel, scaffoldState.snackbarHostState)
    render()
}

@Composable
fun <STATE, VM : BaseSateViewModel<STATE>> BaseComposeScreen2(
    scaffoldState: ScaffoldState,
    viewModel: VM,
    renderOnLoading: @Composable () -> Unit = {
        LoadingScreen()
    },
    renderOnFailure: @Composable () -> Unit = {
        FailureScreen()
    },
    renderOnSuccess: @Composable (state: UiState<STATE>) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    HandleSnackBarIfSupported(
        lifecycleOwner = lifecycleOwner,
        viewModel = viewModel,
        snackbarHostState = scaffoldState.snackbarHostState
    )
    HandleToastIfSupported(lifecycleOwner = lifecycleOwner, viewModel = viewModel)

    val state by lifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        stateFlow = viewModel.uiState,
        initialState = viewModel.initialState
    )

    when (state) {
        is UiState.Loading -> renderOnLoading.invoke()
        is UiState.Success -> renderOnSuccess.invoke(state)
        is UiState.Failure -> renderOnFailure.invoke()
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