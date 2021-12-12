package com.example.newarchplayground.data.remote

import com.example.newarchplayground.data.SearchResult
import com.example.newarchplayground.data.model.PropertyResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PropertyApi {

    @GET("sample_property_list.json")
    suspend fun getProperties(): Response<PropertyResponseModel>

}
