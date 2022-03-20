package com.udacity.asteroidradar.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DateUtils {
    companion object {
        private const val DATE_PATTERN = "yyyy-MM-dd"

        fun getTodayAsString() : String {
            return getDateAsString(Date())
        }

        fun getEndDateAsString(numberOfDays: Int): String {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, numberOfDays)
            return getDateAsString(calendar.time)
        }

        private fun getDateAsString(date: Date) : String {
            return SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(date)
        }
    }
}