package com.example.newarchplayground.data.repository

import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.data.model.PropertyItemResponseModel
import com.example.newarchplayground.data.PropertyRepository
import com.example.newarchplayground.data.remote.datasource.PropertyRemoteDataSource
import javax.inject.Inject

class PropertyRepositoryImp @Inject constructor(
    private val propertyRemoteDataSource: PropertyRemoteDataSource
) : IApiResultFlowWrapper by ApiResultFlowWrapperDelegate(), PropertyRepository {

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
}
