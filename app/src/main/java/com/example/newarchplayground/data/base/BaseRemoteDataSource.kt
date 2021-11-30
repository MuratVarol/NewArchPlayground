package com.example.newarchplayground.data.base

import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.util.Failure

open class BaseRemoteDataSource {

    protected suspend fun <S> fetchResult(call: suspend () -> S): DataResult<Failure, S> {
        return try {
            DataResult.Success(call())
        } catch (exception: Exception) {
            DataResult.Error(toFailure(exception))
        }
    }

    private fun toFailure(exception: Exception): Failure {
        return when (exception) {
            is Failure -> exception
            //is JsonDataException -> Failure.JsonError()
            else -> Failure.UnknownError(exception.localizedMessage)
        }
    }

}
