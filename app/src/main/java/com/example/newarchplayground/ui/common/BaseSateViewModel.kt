package com.example.newarchplayground.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newarchplayground.ui.sample.BaseState
import com.example.newarchplayground.ui.sample.SampleUIState2
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseSateViewModel<STATE>(
    val initialState: UiState<STATE> = UiState.Loading,
) : ViewModel() {

    // Get Current State
    val currentState: UiState<STATE>
        get() = uiState.value

    private val _uiState: MutableStateFlow<UiState<STATE>> by lazy { MutableStateFlow(initialState) }
    val uiState = _uiState.asStateFlow()

    val loadingHandler: (Boolean) -> Unit = {

    }
    val failureHandler: (String) -> Unit = {

    }

    open val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception, COROUTINE_EXCEPTION_HANDLER_MESSAGE)
    }

    fun updateUiState(updateFunc: (UiState<STATE>) -> UiState<STATE>) {
        _uiState.update(updateFunc)
    }

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(exceptionHandler, block = block)
    }

    companion object {
        private const val COROUTINE_EXCEPTION_HANDLER_MESSAGE = "ExceptionHandler"
    }
}
