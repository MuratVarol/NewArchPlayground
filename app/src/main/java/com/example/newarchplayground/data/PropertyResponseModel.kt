package com.example.newarchplayground.data

data class PropertyResponseModel(
    val properties : List<PropertyItemResponseModel>,
)
data class PropertyItemResponseModel(
    val id: String,
    val title: String?,
    val description: String?,
    val image_url: String?,
)