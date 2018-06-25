package com.arsenal.bill.controller

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView

class BaseListControl() {
    lateinit var mRecyclerView: RecyclerView
    var mSwipeRefreshLayout: SwipeRefreshLayout? = null
}
