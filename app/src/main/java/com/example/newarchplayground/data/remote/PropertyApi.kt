package com.example.newarchplayground.data.remote

import com.example.newarchplayground.data.SearchResult
import com.example.newarchplayground.data.model.PropertyResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PropertyApi {
    companion object {
        const val BASE_URL =
            "https://www.propertyfinder.ae/"
    }

    @GET("sample_property_list.json")
    suspend fun getProperties(): Response<PropertyResponseModel>


    @GET("en/api/search?&filter[category_id]=2")
    suspend fun getSearchResult(
        @Query("page[number]") page:Int
    ): Response<SearchResult>

    @GET("en/api/search?page[number]=1&filter[category_id]=2")
    suspend fun getSearchResult2(): Response<SearchResult>

}
