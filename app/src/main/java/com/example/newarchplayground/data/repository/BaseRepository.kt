package com.example.newarchplayground.data.repository

import com.example.newarchplayground.data.common.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

open class BaseRepository {

    fun <T> resultFlow(
        networkCall: suspend () -> ApiResult<T>
    ): Flow<ApiResult<T>> =
        flow {
            emit(ApiResult.Loading)

            withContext(Dispatchers.IO) {
                val responseStatus = networkCall.invoke()
                emit(responseStatus)
            }
        }
}