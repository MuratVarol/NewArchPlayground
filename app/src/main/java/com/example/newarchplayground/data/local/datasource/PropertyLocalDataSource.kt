package com.example.newarchplayground.data.local.datasource

import com.example.newarchplayground.data.base.BaseLocalDataSource
import javax.inject.Inject

class PropertyLocalDataSource @Inject constructor() :
    BaseLocalDataSource() {

    suspend fun getProperties() = Unit
}