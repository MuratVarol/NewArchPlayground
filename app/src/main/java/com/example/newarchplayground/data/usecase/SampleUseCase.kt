package com.example.newarchplayground.data.usecase

import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.common.ResultAlias
import com.example.newarchplayground.data.util.Failure
import com.example.newarchplayground.ui.common.IUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random


class SampleUseCase @Inject constructor() : IUseCase<List<String>> {

    override suspend fun invoke(): Flow<DataResult<Failure, List<String>>> {
        return flow<ResultAlias<List<String>>> {
            emit(DataResult.Loading)
            val list = mutableListOf<String>().apply {
                repeat(4) {
                    add(Random.nextInt(0, 5).toString())
                }
            }
            emit(DataResult.Success(list))
        }
    }
}