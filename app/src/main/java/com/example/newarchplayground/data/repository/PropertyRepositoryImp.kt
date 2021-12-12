package com.example.newarchplayground.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.data.PropertyData
import com.example.newarchplayground.data.model.PropertyItemResponseModel
import com.example.newarchplayground.data.PropertyRepository
import com.example.newarchplayground.data.remote.PropertyApi
import com.example.newarchplayground.data.remote.datasource.PagingPropertySource
import com.example.newarchplayground.data.remote.datasource.PropertyRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PropertyRepositoryImp @Inject constructor(
    private val propertyRemoteDataSource: PropertyRemoteDataSource,
    private val source:PagingPropertySource
) : IResultFlowWrapper by ResultFlowWrapperDelegate(), PropertyRepository {

    override fun getProperties() = flowResult {
        propertyRemoteDataSource.getProperties().transform { response -> response?.properties?.map { it.toUiModel() } }
    }

    private fun PropertyItemResponseModel.toUiModel(): PropertyUiModel {
        return PropertyUiModel(
            id,
            title.toString(),
            description.toString(),
            image_url.toString()
        )
    }

    fun getSearch(): Flow<PagingData<PropertyData>>{
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = true), pagingSourceFactory = {
                source
            }
        ).flow
    }
}
