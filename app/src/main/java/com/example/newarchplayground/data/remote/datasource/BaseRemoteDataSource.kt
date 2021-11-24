package com.example.newarchplayground.data.remote.datasource

import com.example.newarchplayground.data.common.ApiResult
import retrofit2.Response
import timber.log.Timber

open class BaseRemoteDataSource {

    protected suspend fun <T> fetchResult(call: suspend () -> Response<T>): ApiResult<T> {
        try {
            val response = call()
            // Network error types will be placed here
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return ApiResult.Success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ApiResult<T> {
        Timber.e(message)
        return ApiResult.Error("Network call has failed for a following reason: $message")
    }
}
