package com.example.newarchplayground.ui.common

import com.example.newarchplayground.ui.delegate.snackbar.CanDisplaySnackBar
import androidx.compose.material.ScaffoldState
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
    scaffoldState: ScaffoldState
) {
    if (viewModel is CanDisplaySnackBar) {
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
}