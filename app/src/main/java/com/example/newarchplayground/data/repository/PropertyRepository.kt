package com.example.newarchplayground.data.repository

import com.example.newarchplayground.data.remote.datasource.PropertyRemoteRemoteDataSource
import javax.inject.Inject

open class PropertyRepository @Inject constructor(
    private val propertyRemoteDataSource: PropertyRemoteRemoteDataSource
) : BaseRepository() {

    fun getProperties() = resultFlow {
        propertyRemoteDataSource.getProperties()
    }
}
