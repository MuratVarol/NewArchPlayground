package com.example.newarchplayground.ui.delegate.snackbar

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface CanDisplaySnackBar {
    val initialSnackBarState: SnackBarState

    val snackBarState: StateFlow<SnackBarState>

    fun showSnackBar(message: String)

    fun dismissSnackBar()
}

class CanDisplaySnackBarImpl(
    override val initialSnackBarState: SnackBarState = SnackBarState()
) : CanDisplaySnackBar {

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