package com.example.newarchplayground.data

import com.example.newarchplayground.data.common.ApiResult
import com.example.newarchplayground.data.util.Failure
import retrofit2.Response

interface SomeOtherRepository {

    fun getSomeValues(): ApiResult<Failure, Response<String>>

}
