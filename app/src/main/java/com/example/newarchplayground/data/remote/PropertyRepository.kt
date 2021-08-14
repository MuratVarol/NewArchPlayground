package com.example.newarchplayground.data.remote

import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.data.PropertyItemResponseModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

open class PropertyRepository(private val api: PropertyApi) {

    fun getCharacterList(): Flow<List<PropertyUiModel>> =
        flow {
            delay(2000)
            emit(api.getProperties())
        }
            .map { it.properties.map { property -> property.toUiModel() } }

    private fun PropertyItemResponseModel.toUiModel(): PropertyUiModel {
        return PropertyUiModel(
            id,
            title.toString(),
            description.toString(),
            image_url.toString()
        )
    }
}
