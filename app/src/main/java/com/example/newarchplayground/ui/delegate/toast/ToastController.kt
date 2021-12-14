package com.example.newarchplayground.ui.delegate.toast

import android.widget.Toast
import kotlinx.coroutines.flow.StateFlow


data class ToastState(
    val message: String = "",
    val duration: Int = Toast.LENGTH_SHORT
) {
    val show = message.isNotBlank()
}

interface IToastController {
    val initialToastState: ToastState

    val toastState: StateFlow<ToastState>

    fun showToast(message: String)
}