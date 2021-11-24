package com.example.newarchplayground.data.common

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val message: String, val cause: Exception? = null) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}