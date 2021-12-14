package com.example.newarchplayground.ui.common.composable

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.newarchplayground.ui.delegate.toast.IToastController
import com.example.newarchplayground.util.lifecycleAwareState

@Composable
fun HandleToastIfSupported(
    lifecycleOwner: LifecycleOwner,
    viewModel: ViewModel
) {
    if (viewModel is IToastController) {
        val toastState by lifecycleAwareState(
            lifecycleOwner = lifecycleOwner,
            stateFlow = viewModel.toastState,
            initialState = viewModel.initialToastState
        )
        val context = LocalContext.current

        if (toastState.show) {
            LaunchedEffect(toastState) {
                Toast.makeText(context, toastState.message, toastState.duration).show()
            }
        }
    }
}