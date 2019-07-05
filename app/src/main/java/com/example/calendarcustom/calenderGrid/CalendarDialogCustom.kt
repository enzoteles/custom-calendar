package com.example.calendarcustom.calenderGrid

import android.app.DatePickerDialog
import android.content.Context
import com.example.calendarcustom.R
import java.util.*

/**
 * Created by enzo teles on 08/06/2019.
 */
class CalendarDialogCustom {
    private var context: Context? = null
    private var dialog: DatePickerDialog? = null

    constructor(minDay: Int, maxDay: Int, selectedDay: Int, day: Int, month: Int, year: Int, label: String, context: Context, onDateSetListener: DatePickerDialog.OnDateSetListener, style: Int = R.style.DialogTheme) {
        this.context = context

        val cSelectDay: Calendar
        val cMinDay: Calendar
        val cMaxDay: Calendar
        val setMinday = true
        val setMaxDay = true

        cSelectDay = Calendar.getInstance(TimeZone.getDefault())
        cMinDay = Calendar.getInstance(TimeZone.getDefault())
        cMinDay.add(Calendar.DAY_OF_MONTH, minDay)
        cMaxDay = Calendar.getInstance(TimeZone.getDefault())
        cMaxDay.add(Calendar.DAY_OF_MONTH, maxDay)

        if (day > -1 && month > -1 && year > -1) {
            cSelectDay.set(year, month, day)
            // setMinday = false;
            //setMaxDay = false;
        } else {
            cSelectDay.add(Calendar.DAY_OF_MONTH, selectedDay)
        }
        dialog = object : DatePickerDialog(context,
                style,
                OnDateSetListener { view, year, monthOfYear, dayOfMonth -> onDateSetListener.onDateSet(view, year, monthOfYear + 1, dayOfMonth) },
                cSelectDay.get(Calendar.YEAR),
                cSelectDay.get(Calendar.MONTH),
                cSelectDay.get(Calendar.DAY_OF_MONTH)) {

        }
        if (setMinday) {
            dialog!!.datePicker.minDate = cMinDay.timeInMillis
        }
        if (setMaxDay) {
            dialog!!.datePicker.maxDate = cMaxDay.timeInMillis
        }

        if(!label.isNullOrBlank()) {
            dialog!!.setTitle(label)
        }

        dialog?.setOnCancelListener {
            dismiss()
        }
    }

    fun show() {
        dialog!!.show()
    }

    fun hide() {
        dialog!!.hide()
    }

    fun dismiss() {
        dialog!!.dismiss()
    }
}
