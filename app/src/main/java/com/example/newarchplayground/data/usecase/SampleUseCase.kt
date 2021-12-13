package com.example.newarchplayground.data.usecase

import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.util.Failure
import com.example.newarchplayground.domain.usecase.BaseUseCase
import com.example.newarchplayground.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

class SampleUseCase @Inject constructor() : BaseUseCase<List<String>>() {

    override suspend fun invoke(): Flow<DataResult<Failure, List<String>>> =
//        resultUiState {
            flow<DataResult<Failure, List<String>>> {
                emit(DataResult.Loading)
                val list = mutableListOf<String>().apply {
                    repeat(4) {
                        add(Random.nextInt(0, 5).toString())
                    }
                }
                emit(DataResult.Success(list))
            }
//        }
}

interface IUseCase<out T> {
    suspend operator fun invoke(): Flow<DataResult<Failure, T>>
}