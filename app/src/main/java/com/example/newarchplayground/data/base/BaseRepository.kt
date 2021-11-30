package com.example.newarchplayground.data.base

import com.example.newarchplayground.data.common.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

open class BaseRepository {

    fun <E, S> resultFlow(
        networkCall: suspend () -> ApiResult<E, S>
    ): Flow<ApiResult<E, S>> =
        flow {
            emit(ApiResult.Loading)
            val responseStatus = networkCall.invoke()
            emit(responseStatus)
        }.flowOn(Dispatchers.IO)
}