package com.arsenal.bill.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.arsenal.bill.R
import com.arsenal.bill.recyclerview.IVHType
import com.arsenal.bill.recyclerview.MultipleItem
import com.arsenal.bill.recyclerview.MultipleItemQuickAdapter

abstract class BaseListActivity : ArsenalBaseActivity() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: MultipleItemQuickAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_list)
        mRecyclerView = findViewById(R.id.rv_list)
        mRecyclerView.setHasFixedSize(true)

        mAdapter = MultipleItemQuickAdapter(this, null, getVHTypes())
        val manager = GridLayoutManager(this, 4)
        mRecyclerView.layoutManager = manager
        mAdapter.setSpanSizeLookup { _, position ->
            val data = mAdapter.data.get(position);
            if (data is MultipleItem) {
                data.spanSize
            } else {
                4
            }
        }
        mRecyclerView.adapter = mAdapter
    }

    abstract fun getVHTypes(): List<IVHType?>
}