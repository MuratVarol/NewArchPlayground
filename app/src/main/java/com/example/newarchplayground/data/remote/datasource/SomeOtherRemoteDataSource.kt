package com.example.newarchplayground.data.remote.datasource

import com.example.newarchplayground.data.base.BaseRemoteDataSource
import com.example.newarchplayground.data.common.ApiResult
import com.example.newarchplayground.data.remote.PropertyApi
import com.example.newarchplayground.data.remote.SomeOtherApi
import com.example.newarchplayground.data.util.Failure
import retrofit2.Response
import javax.inject.Inject

class SomeOtherRemoteDataSource @Inject constructor(private val api: SomeOtherApi) :
    BaseRemoteDataSource() {

    suspend fun getSomeValues(): ApiResult<Failure, Response<String>> = fetchResult { api.getSomeValues() }
}