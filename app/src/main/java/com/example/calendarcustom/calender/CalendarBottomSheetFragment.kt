package com.example.calendarcustom.calender

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.Toast
import com.example.calendarcustom.R
import kotlinx.android.synthetic.main.canlendar_bottom_sheet.*
import java.util.ArrayList
import android.widget.LinearLayout
import android.util.DisplayMetrics
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback




@SuppressLint("ValidFragment")
class CalendarBottomSheetFragment(val dateList: ArrayList<String>) : BottomSheetDialogFragment(), OnCalendarCallback{

    var snapPosition: Int?= null
    lateinit var adapter: CalendarAdapterCustom


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.canlendar_bottom_sheet, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CalendarBottomSheetFragment.positionSnap = 0

        verificationSizeScreen()
        createBottomSheet()
        managerButton()
        initRecyclerView()


    }

    /**
     * método para gerenciar o click dos butões
     * */
    private fun managerButton() {

        btn_rm_close.setOnClickListener {
            dismiss()
        }

        btn_ok.setOnClickListener {
            val obj = adapter.items.get(this.snapPosition!!)
            Toast.makeText(requireContext(), "${obj}", Toast.LENGTH_LONG).show()
            dismiss()
        }
    }

    /**
     * método para criar o componente bottom sheet
     * */
    fun createBottomSheet(){

        view!!.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val dialog = dialog as BottomSheetDialog
                val bottomSheet = dialog.findViewById<View>(android.support.design.R.id.design_bottom_sheet) as FrameLayout?
                val behavior = BottomSheetBehavior.from(bottomSheet!!)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.peekHeight = 0

                behavior.setBottomSheetCallback(object : BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        // React to state change
                        Log.i("onStateChanged", "${newState}")
                        if(newState >= 4){
                            dismiss()
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        // React to dragging events
                        Log.i("onSlide", "${slideOffset}")
                    }
                })

            }
        })

    }


    /**
     * método que inicia o recyclerview e seta a lista no adapter
     * */
    fun initRecyclerView(){

        rv_calendar_custom.layoutManager = LinearLayoutManager(requireContext())
        adapter = CalendarAdapterCustom(dateList , requireContext())
        rv_calendar_custom.adapter = adapter

        //animacao na lista
        val startSnapHelper = StartSnapHelperB()
        startSnapHelper.attachToRecyclerView(rv_calendar_custom)

        rv_calendar_custom.attachSnapHelperWithListener(startSnapHelper,SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL, null, this)

    }

    /**
     * método que faz o cálculo do tamanho da tela para setar o tamanha do modal
     * */
    private fun verificationSizeScreen() {
        val sizeScreen = getScreenDimension()
        val params = content_constrant.getLayoutParams()
        params.height = (sizeScreen[1]!!.toInt()/2) - 250
        content_constrant.layoutParams = params
    }

    companion object {

        var positionSnap: Int?= null
        fun newInstance(dateList: ArrayList<String>): CalendarBottomSheetFragment {
            return CalendarBottomSheetFragment(dateList)
        }
    }

    /**
     * Método que recebe a resposta do callback do componente de calendário
     * */
    override fun onResponseCalendar(snapPosition: Int) {
        this.snapPosition = snapPosition
        adapter.notifyDataSetChanged()

    }

    /**
     * método que pega o tamanho da tela por pixel
     * */
    private fun getScreenDimension(): Array<String?> {
        val dm = DisplayMetrics()
        activity!!.getWindowManager().getDefaultDisplay().getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        val dens = dm.densityDpi
        val wi = width.toDouble() / dens.toDouble()
        val hi = height.toDouble() / dens.toDouble()
        val x = Math.pow(wi, 2.0)
        val y = Math.pow(hi, 2.0)
        val screenInches = Math.sqrt(x + y)

        val screenInformation = arrayOfNulls<String>(3)
        screenInformation[0] = "${width}"
        screenInformation[1] = "${height}"
        screenInformation[2] = String.format("%.2f", screenInches) + " inches"

        return screenInformation
    }

}