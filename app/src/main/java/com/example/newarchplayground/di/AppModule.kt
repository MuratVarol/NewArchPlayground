package com.example.newarchplayground.di

import android.content.Context
import com.example.newarchplayground.App
import com.serjltt.moshi.adapters.FallbackEnum
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(
        @ApplicationContext context: Context
    ): App {
        return context as App
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Wrapped.ADAPTER_FACTORY)
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .add(FallbackEnum.ADAPTER_FACTORY)
            .build()
    }
}
