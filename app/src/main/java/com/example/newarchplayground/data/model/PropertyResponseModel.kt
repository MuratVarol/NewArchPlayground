package com.example.newarchplayground.data.model

data class PropertyResponseModel(
    val properties : List<PropertyItemResponseModel>,
)
data class PropertyItemResponseModel(
    val id: String,
    val title: String?,
    val description: String?,
    val image_url: String?,
)