package com.example.newarchplayground.ui.common

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<State>(
    val initialState: UiState<State> = UiState.Loading,
) : ViewModel() {

    // Toast properties
    val initialToastState: ToastState = ToastState()

    private val _toastState by lazy { MutableStateFlow(initialToastState) }

    val toastState: StateFlow<ToastState> = _toastState.asStateFlow()

    private val currentToastState: ToastState
        get() = toastState.value

    // Snackbar properties
    val initialSnackBarState: SnackBarState = SnackBarState()

    private val _snackBarState by lazy { MutableStateFlow(initialSnackBarState) }

    val snackBarState: StateFlow<SnackBarState> = _snackBarState.asStateFlow()

    private val currentSnackBarState: SnackBarState
        get() = snackBarState.value

    // Get Current State
    val currentState: UiState<State>
        get() = uiState.value

    private val _uiState: MutableStateFlow<UiState<State>> by lazy { MutableStateFlow(initialState) }
    val uiState = _uiState.asStateFlow()

    open val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception, COROUTINE_EXCEPTION_HANDLER_MESSAGE)
    }

    fun setUiState(state: UiState<State>) {
        _uiState.update {
            state
        }
    }

    fun updateSuccessUiState(updateState: (State) -> State) {
        _uiState.update { UiState.Success(updateState(currentState.successData)) }
    }

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(exceptionHandler, block = block)
    }

    protected fun safeFlowLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(exceptionHandler, block = block)
    }

    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        _toastState.value = currentToastState.copy(
            show = true,
            message = message,
            duration = duration
        )
    }

    fun dismissToast() {
        _toastState.value = currentToastState.copy(show = false)
    }

    fun showSnackBar(message: String) {
        _snackBarState.value = currentSnackBarState.copy(show = true, message = message)
    }

    fun dismissSnackBar() {
        _snackBarState.value = currentSnackBarState.copy(show = false)
    }

    companion object {
        private const val COROUTINE_EXCEPTION_HANDLER_MESSAGE = "ExceptionHandler"
    }
}

data class SnackBarState(
    val show: Boolean = false,
    val message: String = ""
)

data class ToastState(
    val show: Boolean = false,
    val message: String = "",
    val duration: Int = Toast.LENGTH_SHORT
)