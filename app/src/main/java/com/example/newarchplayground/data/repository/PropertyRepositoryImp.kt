package com.example.newarchplayground.data.repository

import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.data.model.PropertyItemResponseModel
import com.example.newarchplayground.data.PropertyRepository
import com.example.newarchplayground.data.base.BaseRepository
import com.example.newarchplayground.data.remote.datasource.PropertyRemoteDataSource
import javax.inject.Inject

class PropertyRepositoryImp @Inject constructor(
    private val propertyRemoteDataSource: PropertyRemoteDataSource
) : BaseRepository(), PropertyRepository {

    override fun getProperties() = resultFlow {
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
