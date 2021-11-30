package com.example.newarchplayground.data

import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.util.Failure
import retrofit2.Response

interface SomeOtherRepository {

    fun getSomeValues(): DataResult<Failure, Response<String>>

}
