package com.bill.billdemo

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.arsenal.bill.ArsenalBaseActivity
import kotlinx.android.synthetic.main.activity_adapter.*
import java.util.*

class MainActivityArsenal : ArsenalBaseActivity() {
    lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adapter)
        mRecyclerView = rv_list
        mRecyclerView.setHasFixedSize(true);

        val data = getMultipleItemData()
        val multipleItemAdapter = MultipleItemQuickAdapter(this, data)
        val manager = GridLayoutManager(this, 4)
        mRecyclerView.layoutManager = manager
        multipleItemAdapter.setSpanSizeLookup { gridLayoutManager, position -> data.get(position).getSpanSize() }
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
