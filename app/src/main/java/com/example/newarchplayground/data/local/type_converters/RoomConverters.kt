package com.example.newarchplayground.data.local.type_converters

import androidx.room.TypeConverter
import com.example.newarchplayground.data.local.dao.db_entity.PropertyDbEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class RoomConverters {

    @TypeConverter
    fun fromPropertiesPriceList(data: List<PropertyDbEntity>): String {
        return Adapters.EQUITIES_PRICE_LIST_MODEL.toJson(data)
    }

    @TypeConverter
    fun toPropertiesPriceList(json: String): List<PropertyDbEntity>? {
        return Adapters.EQUITIES_PRICE_LIST_MODEL.fromJson(json)
    }

    @TypeConverter
    fun fromStringList(data: List<String>): String {
        return Adapters.INSTRUMENT_LIST_MODEL.toJson(data)
    }

    @TypeConverter
    fun toStringList(json: String): List<String>? {
        return Adapters.INSTRUMENT_LIST_MODEL.fromJson(json)
    }

}

/** Moshi Adapters **/
object Adapters {
    private val moshi = Moshi.Builder().build()
    val EQUITIES_PRICE_LIST_MODEL: JsonAdapter<List<PropertyDbEntity>> = adapter()
    val INSTRUMENT_LIST_MODEL: JsonAdapter<List<String>> = adapter()

    private inline fun <reified T> adapter(): JsonAdapter<T> = moshi.adapter(T::class.java)

}