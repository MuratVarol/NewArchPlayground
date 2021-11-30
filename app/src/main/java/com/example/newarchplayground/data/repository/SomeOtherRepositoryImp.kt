package com.example.newarchplayground.data.repository

import com.example.newarchplayground.data.SomeOtherRepository
import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.util.Failure
import retrofit2.Response
import javax.inject.Inject

class SomeOtherRepositoryImp @Inject constructor(
) : IResultFlowWrapper by ResultFlowWrapperDelegate(), SomeOtherRepository {

    override fun getSomeValues(): DataResult<Failure, Response<String>> {
        TODO("Not yet implemented")
    }

}
