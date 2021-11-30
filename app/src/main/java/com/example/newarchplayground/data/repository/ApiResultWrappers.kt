package com.example.newarchplayground.data.repository

import androidx.lifecycle.LiveData
import com.example.newarchplayground.data.common.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface IApiResultFlowWrapper {
    fun <E, S> flowResult(dataCall: suspend () -> ApiResult<E, S>): Flow<ApiResult<E, S>>
}

class ApiResultFlowWrapperDelegate : IApiResultFlowWrapper {
    override fun <E, S> flowResult(dataCall: suspend () -> ApiResult<E, S>): Flow<ApiResult<E, S>> =
        flow {
            emit(ApiResult.Loading)

            val responseStatus = dataCall.invoke()
            emit(responseStatus)
        }.flowOn(Dispatchers.IO)
}


interface ILiveDataRequestWrapper {
    fun <E, S> liveDataResult(networkCall: suspend () -> ApiResult<E, S>): LiveData<ApiResult<E, S>>
}

class LiveDataRequestWrapperDelegate : ILiveDataRequestWrapper {
    override fun <E, S> liveDataResult(networkCall: suspend () -> ApiResult<E, S>): LiveData<ApiResult<E, S>> {
        TODO("Not yet implemented")
    }
}