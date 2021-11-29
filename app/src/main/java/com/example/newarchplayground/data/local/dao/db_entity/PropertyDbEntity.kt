package com.example.newarchplayground.data.local.dao.db_entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newarchplayground.PropertyUiModel

@Entity(tableName = "properties")
data class PropertyDbEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
) {
    fun toUiModel(): PropertyUiModel {
        return PropertyUiModel(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl
        )
    }
}