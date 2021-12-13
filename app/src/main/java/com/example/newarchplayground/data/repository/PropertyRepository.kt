package com.example.newarchplayground.data.repository

import androidx.lifecycle.LiveData
import com.example.newarchplayground.data.SearchResult
import com.example.newarchplayground.data.model.PropertyResponseModel
import com.example.newarchplayground.data.remote.datasource.PropertyRemoteRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PropertyRepository @Inject constructor(
    private val propertyRemoteDataSource: PropertyRemoteRemoteDataSource
) : IResultFlowWrapper by ResultFlowWrapperDelegate(){

}