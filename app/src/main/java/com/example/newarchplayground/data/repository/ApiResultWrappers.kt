package com.example.newarchplayground.data.repository

import androidx.lifecycle.LiveData
import com.example.newarchplayground.data.common.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface IApiResultFlowWrapper {
    fun <T> flowResult(dataCall: suspend () -> ApiResult<T>): Flow<ApiResult<T>>
}

class ApiResultFlowWrapperDelegate : IApiResultFlowWrapper {
    override fun <T> flowResult(dataCall: suspend () -> ApiResult<T>): Flow<ApiResult<T>> =
        flow {
            emit(ApiResult.Loading)

            val responseStatus = dataCall.invoke()
            emit(responseStatus)
        }.flowOn(Dispatchers.IO)
}


interface ILiveDataRequestWrapper {
    fun <T> liveDataResult(networkCall: suspend () -> ApiResult<T>): LiveData<ApiResult<T>>
}

class LiveDataRequestWrapperDelegate : ILiveDataRequestWrapper {
    override fun <T> liveDataResult(networkCall: suspend () -> ApiResult<T>): LiveData<ApiResult<T>> {
        TODO("Not yet implemented")
    }
}