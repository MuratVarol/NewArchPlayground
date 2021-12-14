package com.example.newarchplayground.ui.delegate

import com.example.newarchplayground.ui.delegate.toast.IToastController
import com.example.newarchplayground.ui.delegate.toast.ToastState
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

    override fun showToast(message: String) {
        _toastState.value = currentToastState.copy(message = message)
    }
}