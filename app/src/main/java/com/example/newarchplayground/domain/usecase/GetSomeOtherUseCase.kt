package com.example.newarchplayground.domain.usecase

import com.example.newarchplayground.data.common.ApiResult
import com.example.newarchplayground.data.repository.SomeOtherRepositoryImp
import com.example.newarchplayground.data.util.Failure
import retrofit2.Response
import javax.inject.Inject

class GetSomeOtherUseCase @Inject constructor(private val repo: SomeOtherRepositoryImp) {
    fun getSomeValues(): ApiResult<Failure, Response<String>> = repo.getSomeValues()
}