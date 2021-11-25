package com.example.newarchplayground.data.repository

import androidx.lifecycle.LiveData
import com.example.newarchplayground.data.common.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

interface IFlowRequestWrapper {
    fun <T> flowResult(networkCall: suspend () -> ApiResult<T>): Flow<ApiResult<T>>
}

class FlowRequestWrapperImpl : IFlowRequestWrapper {
    override fun <T> flowResult(networkCall: suspend () -> ApiResult<T>): Flow<ApiResult<T>> =
        flow {
            emit(ApiResult.Loading)

            withContext(Dispatchers.IO) {
                val responseStatus = networkCall.invoke()
                emit(responseStatus)
            }
        }
}


interface ILiveDataRequestWrapper {
    fun <T> liveDataResult(networkCall: suspend () -> ApiResult<T>): LiveData<ApiResult<T>>
}

class LiveDataRequestWrapperImpl : ILiveDataRequestWrapper {
    override fun <T> liveDataResult(networkCall: suspend () -> ApiResult<T>): LiveData<ApiResult<T>> {
        TODO("Not yet implemented")
    }
}