package com.example.tvmaze.utils

import java.text.SimpleDateFormat
import java.util.*

class CommonUtils {

    companion object {

        fun datePartsToDate(year: Int, month: Int, day: Int): Date {
            val c = Calendar.getInstance()
            c.set(Calendar.YEAR, year)
            c.set(Calendar.MONTH, month)
            c.set(Calendar.DAY_OF_MONTH, day)

            return c.time
        }

        fun datePartsToString(year: Int, month: Int, day: Int): String {
            val date = datePartsToDate(year, month, day)
            return dateToString(date)
        }

        fun dateToString(date: Date, pattern: String = "dd/MM/yy"): String {
            val formatter = SimpleDateFormat(pattern, Locale("es", "MX"))
            return formatter.format(date)
        }
    }
}