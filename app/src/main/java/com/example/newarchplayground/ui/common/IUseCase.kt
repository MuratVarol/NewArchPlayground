package com.example.newarchplayground.ui.common

import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.util.Failure
import kotlinx.coroutines.flow.Flow


interface IUseCase<DATA> {
    suspend operator fun invoke(): Flow<DataResult<Failure, DATA>>
}