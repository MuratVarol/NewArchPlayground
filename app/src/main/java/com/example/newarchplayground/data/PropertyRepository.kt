package com.example.newarchplayground.data

import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.data.common.DataResult
import com.example.newarchplayground.data.util.Failure
import kotlinx.coroutines.flow.Flow

interface PropertyRepository {

    fun getProperties(): Flow<DataResult<Failure, List<PropertyUiModel>?>>

}
