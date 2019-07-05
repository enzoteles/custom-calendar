package com.example.calendarcustom.calenderGrid

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by enzo.teles on 08/06/2019.
 */

class DataCustom {
    var year: Int = 0
    var month: Int = 0
    var day: Int = 0

    constructor() {
        loadData(0, 0, 0)
    }

    constructor(year: Int, month: Int, day: Int) {
        loadData(year, month, day)
    }

    constructor(date: Date) {
        val cal = CalendarCustom.getCalendarFrom(date)
        loadData(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH))
    }

    constructor(date: String){
        val formatter = SimpleDateFormat("dd/MM/yy")
        val date = formatter.parse(date) as Date
        val cal = CalendarCustom.getCalendarFrom(date)
        loadData(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH))

    }

    constructor(cal: Calendar) {
        loadData(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH))
    }

    fun loadData(year: Int, month: Int, day: Int) {
        val now = CalendarCustom.now
        if (year == 0) {
            this.year = CalendarCustom.getYear(now)
        } else {
            this.year = year
        }
        if (month == 0) {
            this.month = CalendarCustom.getMonth(now)
        } else {
            this.month = month
        }
        if (day == 0) {
            this.day = CalendarCustom.getDay(now)
        } else {
            this.day = day
        }
    }

    fun toCalendar(): Calendar {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.set(year, month - 1, day)
        return calendar
    }

    fun toDate(): Date {
        val calendar = toCalendar()
        return calendar.time
    }

    override fun toString(): String {
        if (day > 0 && month > 0 && year > 0) {
                return String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year
        }
        return ""
    }

}
