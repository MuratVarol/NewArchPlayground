package com.example.newarchplayground.data.remote

import com.example.newarchplayground.data.PropertyResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface PropertyApi {
    companion object {
        const val BASE_URL =
            "https://gist.githubusercontent.com/pf-Murat/26dd5f45f6f57a1983b5c9ada67f9314/raw/65daebc5f6eb60bf4d01d0a7a7b8c0bfb61fba2c/"
    }

    @GET("sample_property_list.json")
    suspend fun getProperties(): Response<PropertyResponseModel>

}
