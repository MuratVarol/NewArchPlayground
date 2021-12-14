package com.example.newarchplayground.ui.delegate.toast

import android.widget.Toast
import kotlinx.coroutines.flow.StateFlow


data class ToastState(
    val show: Boolean = false,
    val message: String = "",
    val duration: Int = Toast.LENGTH_SHORT
)

interface IToastController {
    val initialToastState: ToastState

    val toastState: StateFlow<ToastState>

    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT)

    fun dismissToast()
}