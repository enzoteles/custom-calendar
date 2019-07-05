package com.example.calendarcustom.calender

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.calendarcustom.R
import kotlinx.android.synthetic.main.item_calendar_custom.view.*



class CalendarAdapterCustom(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_calendar_custom, p0, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.tvCustom?.text = items.get(position)

        if(CalendarBottomSheetFragment.positionSnap != null){
            if(position == CalendarBottomSheetFragment.positionSnap){
                holder?.tvCustom?.textSize = 15f
                holder?.tvCustom?.setTypeface(holder?.tvCustom?.typeface, Typeface.BOLD)
                holder?.tvCustom.alpha = 1f
            }else{
                holder?.tvCustom.alpha = 0.4f
            }
        }
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


    private fun getItemAtPosition(position: Int): String {
        return items.get(position)
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvCustom = view.tv_item_custom
    val lineCalendar = view.line
}
