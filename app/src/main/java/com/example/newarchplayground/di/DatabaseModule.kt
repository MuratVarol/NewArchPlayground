package com.example.newarchplayground.di

import android.content.Context
import androidx.room.Room
import com.example.newarchplayground.data.local.AppDatabase
import com.example.newarchplayground.data.local.dao.PropertiesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DB_NAME = "pf.db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providesPropertiesDao(appDatabase: AppDatabase): PropertiesDao {
        return appDatabase.propertiesDao()
    }

}
