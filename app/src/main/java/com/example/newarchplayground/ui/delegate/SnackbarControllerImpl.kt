package com.example.newarchplayground.ui.delegate

import com.example.newarchplayground.ui.delegate.snackbar.ISnackBarController
import com.example.newarchplayground.ui.delegate.snackbar.SnackBarState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SnackbarControllerImpl(
    override val initialSnackBarState: SnackBarState = SnackBarState()
) : ISnackBarController {

    private val _snackBarState: MutableStateFlow<SnackBarState> by lazy {
        MutableStateFlow(initialSnackBarState)
    }

    override val snackBarState: StateFlow<SnackBarState> = _snackBarState.asStateFlow()

    private val currentSnackBarState: SnackBarState
        get() = snackBarState.value

    override fun showSnackBar(message: String) {
        _snackBarState.value = currentSnackBarState.copy(show = true, message = message)
    }

    override fun dismissSnackBar() {
        _snackBarState.value = currentSnackBarState.copy(show = false)
    }
}