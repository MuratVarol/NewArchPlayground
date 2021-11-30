package com.example.newarchplayground.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newarchplayground.BuildConfig
import com.example.newarchplayground.data.local.dao.PropertiesDao
import com.example.newarchplayground.data.local.dao.db_entity.PropertyDbEntity
import com.example.newarchplayground.data.local.type_converters.RoomConverters

@Database(
    entities = [
        PropertyDbEntity::class,], version = BuildConfig.VERSION_CODE, exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun propertiesDao(): PropertiesDao

}