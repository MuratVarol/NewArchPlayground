package com.example.newarchplayground.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newarchplayground.data.PropertyData
import com.example.newarchplayground.data.remote.PropertyApi
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.delay
import javax.inject.Inject

class PagingPropertySource @Inject constructor(private val api: PropertyApi) :
    PagingSource<Int, PropertyData>() {
    override fun getRefreshKey(state: PagingState<Int, PropertyData>): Int? {
// We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PropertyData> {
        val position = params.key ?: 1

        return try {
            val mutableListOf = mutableListOf<PropertyData>()
            val get = (api.getSearchResult(position)
                .body()?.data?.get("meta") as LinkedTreeMap<*, *>).get("json_schema") as List<LinkedTreeMap<*, *>>
            println("get = ${get}")
            delay(3000)
            val map = get.filter {
                it["image"] != null
            }.map {
                PropertyData(it["image"].toString(), it["name"]?.toString() ?: "")
            }

            mutableListOf.addAll(map)
            val nextKey = if (mutableListOf.isEmpty()) null else {
                (position + 1)
            }
            LoadResult.Page(
                data = mutableListOf,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}