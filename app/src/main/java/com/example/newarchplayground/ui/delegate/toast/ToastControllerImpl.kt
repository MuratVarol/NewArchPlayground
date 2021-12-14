package com.example.newarchplayground.ui.delegate.toast

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ToastControllerImpl(
    override val initialToastState: ToastState = ToastState()
) : IToastController {

    private val _toastState: MutableStateFlow<ToastState> by lazy {
        MutableStateFlow(initialToastState)
    }

    override val toastState: StateFlow<ToastState> = _toastState.asStateFlow()

    private val currentToastState: ToastState
        get() = toastState.value

    override fun showToast(message: String, duration: Int) {
        _toastState.value = currentToastState.copy(show = true, message = message, duration = duration)
    }

    override fun dismissToast() {
        _toastState.value = currentToastState.copy(show = false)
    }
}