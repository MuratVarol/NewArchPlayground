package com.example.newarchplayground.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface SomeOtherApi {

    @GET("endpoint")
    suspend fun getSomeValues(): Response<String>

}
