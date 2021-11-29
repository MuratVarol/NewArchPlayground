package com.example.newarchplayground.data.repository

import androidx.lifecycle.LiveData
import com.example.newarchplayground.data.PropertyResponseModel
import com.example.newarchplayground.data.common.ApiResult
import com.example.newarchplayground.data.remote.datasource.PropertyRemoteRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class PropertyRepository @Inject constructor(
    private val propertyRemoteDataSource: PropertyRemoteRemoteDataSource
) : IApiResultFlowWrapper by ApiResultFlowWrapperImpl(),
    ILiveDataRequestWrapper by LiveDataRequestWrapperImpl() {

    fun getProperties(): Flow<ApiResult<PropertyResponseModel>> = flowResult {
        propertyRemoteDataSource.getProperties()
    }

    fun getPropertiesLive(): LiveData<ApiResult<PropertyResponseModel>> = liveDataResult {
        propertyRemoteDataSource.getProperties()
    }
}