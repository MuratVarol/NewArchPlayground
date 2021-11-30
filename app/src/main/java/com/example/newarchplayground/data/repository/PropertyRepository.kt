package com.example.newarchplayground.data.repository

import com.example.newarchplayground.data.remote.datasource.PropertyRemoteRemoteDataSource
import javax.inject.Inject

class PropertyRepository @Inject constructor(
    private val propertyRemoteDataSource: PropertyRemoteRemoteDataSource
) : IApiResultFlowWrapper by ApiResultFlowWrapperDelegate(){

    fun getProperties() = flowResult {
        propertyRemoteDataSource.getProperties()
    }
}