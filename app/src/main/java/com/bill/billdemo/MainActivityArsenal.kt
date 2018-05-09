package com.bill.billdemo

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arsenal.bill.ArsenalBaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityArsenal : ArsenalBaseActivity() {
    lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adapter)
        mRecyclerView = recycler_view
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(  LinearLayoutManager(this));

    }

    override fun enableSwipeBack(): Boolean {
        return false
    }
}
