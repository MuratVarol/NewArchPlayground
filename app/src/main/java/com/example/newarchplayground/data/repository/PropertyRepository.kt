package com.example.newarchplayground.data.repository

import com.example.newarchplayground.data.remote.datasource.PropertyRemoteRemoteDataSource
import javax.inject.Inject

class PropertyRepository @Inject constructor(
    private val propertyRemoteDataSource: PropertyRemoteRemoteDataSource
) : IResultFlowWrapper by ResultFlowWrapperDelegate(){

    fun getProperties() = flowResult {
        propertyRemoteDataSource.getProperties()
    }
}