package com.example.newarchplayground.ui.delegate.snackbar

import kotlinx.coroutines.flow.StateFlow


data class SnackBarState(
    val show: Boolean = false,
    val message: String = ""
)

interface ISnackBarController {
    val initialSnackBarState: SnackBarState

    val snackBarState: StateFlow<SnackBarState>

    fun showSnackBar(message: String)

    fun dismissSnackBar()
}