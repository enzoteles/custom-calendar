package com.example.calendarcustom.calender

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper


fun RecyclerView.attachSnapHelperWithListener(
    snapHelper: SnapHelper,
    behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
    onSnapPositionChangeListener: OnSnapPositionChangeListener?,
    callback: OnCalendarCallback
) {
    snapHelper.attachToRecyclerView(this)
    val snapOnScrollListener = SnapOnScrollListener(snapHelper, behavior, onSnapPositionChangeListener, callback)
    addOnScrollListener(snapOnScrollListener)
}