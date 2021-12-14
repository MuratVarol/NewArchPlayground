package com.example.newarchplayground.ui.common.composable

import com.example.newarchplayground.ui.delegate.snackbar.ISnackBarController
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.newarchplayground.util.lifecycleAwareState

@Composable
fun HandleSnackBarIfSupported(
    lifecycleOwner: LifecycleOwner,
    viewModel: ViewModel,
    snackbarHostState: SnackbarHostState
) {
    if (viewModel is ISnackBarController) {
        val snackBarState by lifecycleAwareState(
            lifecycleOwner = lifecycleOwner,
            stateFlow = viewModel.snackBarState,
            initialState = viewModel.initialSnackBarState
        )

        if (snackBarState.show) {
            LaunchedEffect(snackbarHostState) {
                when (snackbarHostState.showSnackbar(snackBarState.message)) {
                    SnackbarResult.Dismissed -> viewModel.dismissSnackBar()
                    SnackbarResult.ActionPerformed -> {
                    }
                }
            }
        }
    }
}