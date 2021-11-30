package com.example.newarchplayground.data.repository

import androidx.lifecycle.LiveData
import com.example.newarchplayground.data.common.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface IResultFlowWrapper {
    fun <E, S> flowResult(dataCall: suspend () -> DataResult<E, S>): Flow<DataResult<E, S>>
}

class ResultFlowWrapperDelegate : IResultFlowWrapper {
    override fun <E, S> flowResult(dataCall: suspend () -> DataResult<E, S>): Flow<DataResult<E, S>> =
        flow {
            emit(DataResult.Loading)

            val responseStatus = dataCall.invoke()
            emit(responseStatus)
        }.flowOn(Dispatchers.IO)
}


interface IResultLiveDataWrapper {
    fun <E, S> liveDataResult(networkCall: suspend () -> DataResult<E, S>): LiveData<DataResult<E, S>>
}

class ResultLiveDataWrapperDelegate : IResultLiveDataWrapper {
    override fun <E, S> liveDataResult(networkCall: suspend () -> DataResult<E, S>): LiveData<DataResult<E, S>> {
        TODO("Not yet implemented")
    }
}