package com.example.newarchplayground.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.newarchplayground.data.local.BaseDao
import com.example.newarchplayground.data.local.dao.db_entity.PropertyDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertiesDao : BaseDao<PropertyDbEntity> {
    @Query("SELECT * FROM properties")
    fun getLocalProperties(): Flow<List<PropertyDbEntity>>
}