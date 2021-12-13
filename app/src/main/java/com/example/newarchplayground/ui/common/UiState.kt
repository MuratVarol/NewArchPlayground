package com.example.newarchplayground.ui.common

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Failure(val message: String) : UiState<Nothing>()
}

val <T> UiState<T>.successData: T
    get() = (this as UiState.Success).data

interface UiEffect