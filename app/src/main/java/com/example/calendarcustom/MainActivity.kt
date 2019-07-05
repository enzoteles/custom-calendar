package com.example.calendarcustom

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.util.*
import com.example.calendarcustom.calender.CalendarBottomSheetFragment
import com.example.calendarcustom.calenderGrid.CalendarCustom
import com.example.calendarcustom.calenderGrid.DataCustom
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity() {


    lateinit var data_filtro: DataCustom
    val dateList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_custom)

        data_filtro = DataCustom(Calendar.getInstance(TimeZone.getDefault()))

        val cal = data_filtro.toCalendar()
        val dia = CalendarCustom.getDay(cal)
        val mes = CalendarCustom.getMonth(cal)
        val ano = CalendarCustom.getYear(cal)


        val mesCerto = mes + 1

        val curFormater = SimpleDateFormat("dd/MM/yyyy")

        val mesAtual = if(mesCerto < 10) "0${mesCerto}" else "${mesCerto}"
        dateList.add("${dia}/${mesAtual}/${ano}")
        val calendar = Calendar.getInstance()
        for (i in 0..5) {
            calendar.add(Calendar.DATE, 1)
            dateList.add(curFormater.format(calendar.time))
        }


    }

    override fun onResume() {
        super.onResume()
        val recebaMaisSF = CalendarBottomSheetFragment.newInstance(dateList)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(recebaMaisSF, "modal_receba_mais")
        transaction.commitAllowingStateLoss()
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onDestroy() {
        super.onDestroy()
        CalendarBottomSheetFragment.positionSnap = null
    }

}
