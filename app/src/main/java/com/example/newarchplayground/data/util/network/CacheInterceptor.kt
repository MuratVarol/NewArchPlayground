package com.example.newarchplayground.data.util.network

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

private const val CACHE_LIFE = 10

/**
 *  If response coming from server; cache it with {CACHE_LIFE} seconds
 */
class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(CACHE_LIFE, TimeUnit.MILLISECONDS)
            .build()
        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}
