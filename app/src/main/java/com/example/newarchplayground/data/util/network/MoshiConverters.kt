package com.example.newarchplayground.data.util.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

class MoshiConverters {

    private val format = SimpleDateFormat(
        Constants.Date.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT,
        Locale.getDefault()
    )

    @FromJson
    fun timestampToDate(value: String?): Date? = if (value == null) null else format.parse(value)

    @ToJson
    fun dateToTimestamp(date: Date?): String? = date?.let { format.format(date) }
}