package com.example.newarchplayground.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseStateViewModel<State>(
    val initialState: UiState<State> = UiState.Loading,
) : ViewModel() {

    // Get Current State
    val currentState: UiState<State>
        get() = uiState.value

    private val _uiState: MutableStateFlow<UiState<State>> by lazy { MutableStateFlow(initialState) }
    val uiState = _uiState.asStateFlow()

    open val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception, COROUTINE_EXCEPTION_HANDLER_MESSAGE)
    }

    fun updateUiState(updateFunc: (UiState<State>) -> UiState<State>) {
        _uiState.update(updateFunc)
    }

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(exceptionHandler, block = block)
    }

    companion object {
        private const val COROUTINE_EXCEPTION_HANDLER_MESSAGE = "ExceptionHandler"
    }
}
