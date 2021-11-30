package com.example.newarchplayground.data.util.network

import java.util.*

class Constants {
    object Date {
        const val ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        const val ISO_8601_EXTENDED_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        const val DATE_FORMAT_UI_WITH_STRING_MONTH = "dd MMM HH:mm:ss"
        const val DATE_FORMAT_DAY_MONTH_WEEKDAY_AND_TIME = "dd MMMM EEEE HH:mm:ss"
        const val DATE_FORMAT_DAY_OF_MONTH_AND_MONTH = "dd MMMM"
        const val DATE_FORMAT_DAY_AS_TRANSACTION_RESULT = "dd.MM.yyyy / HH:mm"
        const val DATE_FORMAT_WITH_DOTS = "dd.MM.yyy"
        const val TIME_FORMAT_DAILY = "HH:mm"
        const val TIME_FORMAT_USA = "yyyyMMdd"
    }

    companion object {
        val LOCALE_ARABIC: Locale = Locale.forLanguageTag("ar")
        val LOCALE_ENGLISH: Locale = Locale.forLanguageTag("en")
    }

}
