package com.example.calendarcustom.calenderGrid

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by enzo teles on 08/06/2019.
 */

class CalendarCustom {
    init {
        throw UnsupportedOperationException() as Throwable
    }

    companion object {

        private val TAG = "CalendarCustom"
        val SIMPLE_DATE_FORMAT = "yyyy-MM-dd"

        val now: Calendar
            get() = Calendar.getInstance(TimeZone.getDefault())

        fun getYear(calendar: Calendar): Int {
            return calendar.get(Calendar.YEAR)
        }

        fun getMonth(calendar: Calendar): Int {
            return calendar.get(Calendar.MONTH)
        }

        fun getDay(calendar: Calendar): Int {
            return calendar.get(Calendar.DAY_OF_MONTH)
        }

        fun getCalendarFrom(date: Date): Calendar {
            val cal = Calendar.getInstance()
            cal.time = date
            return cal
        }

        fun getCalendarFrom(date: String): Calendar {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat(SIMPLE_DATE_FORMAT)
            try {
                calendar.time = sdf.parse(date)
            } catch (e: ParseException) {
                Log.e(TAG, e.message)
            }

            return calendar
        }
    }
}
