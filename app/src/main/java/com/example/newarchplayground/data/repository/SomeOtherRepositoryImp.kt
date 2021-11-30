package com.example.newarchplayground.data.repository

import com.example.newarchplayground.data.SomeOtherRepository
import com.example.newarchplayground.data.common.ApiResult
import com.example.newarchplayground.data.util.Failure
import retrofit2.Response
import javax.inject.Inject

class SomeOtherRepositoryImp @Inject constructor(
) : IApiResultFlowWrapper by ApiResultFlowWrapperDelegate(), SomeOtherRepository {

    override fun getSomeValues(): ApiResult<Failure, Response<String>> {
        TODO("Not yet implemented")
    }

}
