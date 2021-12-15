package com.example.newarchplayground.ui.common.composable

import android.widget.Toast
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import com.example.newarchplayground.ui.common.BaseViewModel
import com.example.newarchplayground.ui.common.UiState
import com.example.newarchplayground.ui.common.successData
import com.example.newarchplayground.util.lifecycleAwareState


@Composable
fun <STATE, VM : BaseViewModel<STATE>> BaseComposeScreen(
    scaffoldState: ScaffoldState,
    viewModel: VM,
    renderOnLoading: @Composable () -> Unit = { },
    renderOnFailure: @Composable () -> Unit = { },
    renderOnSuccess: @Composable (state: STATE) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    HandleSnackbar(
        lifecycleOwner = lifecycleOwner,
        viewModel = viewModel,
        scaffoldState = scaffoldState
    )

    HandleToast(
        lifecycleOwner = lifecycleOwner,
        viewModel = viewModel
    )

    val state by lifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        stateFlow = viewModel.uiState,
        initialState = viewModel.initialState
    )

    when (state) {
        is UiState.Loading -> renderOnLoading.invoke()
        is UiState.Success -> renderOnSuccess.invoke(state.successData)
        is UiState.Failure -> renderOnFailure.invoke()
    }
}

@Composable
fun <STATE, VM : BaseViewModel<STATE>> HandleSnackbar(
    scaffoldState: ScaffoldState,
    lifecycleOwner: LifecycleOwner,
    viewModel: VM
) {
    val snackBarState by lifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        stateFlow = viewModel.snackBarState,
        initialState = viewModel.initialSnackBarState
    )

    if (snackBarState.show) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            when (scaffoldState.snackbarHostState.showSnackbar(snackBarState.message)) {
                SnackbarResult.Dismissed -> viewModel.dismissSnackBar()
                SnackbarResult.ActionPerformed -> {
                }
            }
        }
    }
}

@Composable
fun <STATE, VM : BaseViewModel<STATE>> HandleToast(
    lifecycleOwner: LifecycleOwner,
    viewModel: VM
) {
    val toastState by lifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        stateFlow = viewModel.toastState,
        initialState = viewModel.initialToastState
    )
    val context = LocalContext.current

    if (toastState.show) {
        LaunchedEffect(toastState.message) {
            Toast.makeText(context, toastState.message, toastState.duration).show()
            viewModel.dismissToast()
        }
    }
}