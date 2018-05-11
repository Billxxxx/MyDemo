package com.bill.billdemo.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.arsenal.bill.ArsenalBaseActivity
import com.bill.billdemo.MultipleItem
import com.bill.billdemo.MultipleItemQuickAdapter
import com.bill.billdemo.R
import kotlinx.android.synthetic.main.ac_list.*
import java.util.*

@Route(path = "/base/list")
class ListActivity : ArsenalBaseActivity() {
    lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_list)
        mRecyclerView = rv_list
        mRecyclerView.setHasFixedSize(true);

        val data = getMultipleItemData()
        val multipleItemAdapter = MultipleItemQuickAdapter(this, data)
        val manager = GridLayoutManager(this, 4)
        mRecyclerView.layoutManager = manager
        multipleItemAdapter.setSpanSizeLookup { _, position -> data.get(position).getSpanSize() }
        mRecyclerView.adapter = multipleItemAdapter

    }

    override fun enableSwipeBack(): Boolean {
        return false
    }

    fun getMultipleItemData(): List<MultipleItem> {
        val list = ArrayList<MultipleItem>()
        for (i in 0..4) {
            list.add(MultipleItem(MultipleItem.IMG, MultipleItem.IMG_SPAN_SIZE))
            list.add(MultipleItem(MultipleItem.TEXT, MultipleItem.TEXT_SPAN_SIZE, "CymChad"))
            list.add(MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE))
            list.add(MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN))
            list.add(MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN))
        }

        return list
    }
}
