package com.example.newarchplayground.ui.base

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Failure(val exception: Exception) : UiState<Nothing>()
}

val <T> UiState<T>.successData: T
    get() = (this as UiState.Success).data

interface UiEffect