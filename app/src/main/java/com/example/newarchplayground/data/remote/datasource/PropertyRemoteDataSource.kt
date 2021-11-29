package com.example.newarchplayground.data.remote.datasource

import com.example.newarchplayground.data.base.BaseRemoteDataSource
import com.example.newarchplayground.data.remote.PropertyApi
import javax.inject.Inject

class PropertyRemoteDataSource @Inject constructor(private val api: PropertyApi) :
    BaseRemoteDataSource() {

    suspend fun getProperties() = fetchResult { api.getProperties().body() }
}