package com.arsenal.bill.activity

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.arsenal.bill.R
import com.arsenal.bill.recyclerview.IVHType
import com.arsenal.bill.recyclerview.MultipleItem
import com.arsenal.bill.recyclerview.MultipleItemQuickAdapter
import com.arsenal.bill.util.dpToPx
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration

abstract class BaseListActivity :
        ArsenalBaseActivity(),
        FlexibleDividerDecoration.PaintProvider,
        FlexibleDividerDecoration.VisibilityProvider,
        HorizontalDividerItemDecoration.MarginProvider {
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
        mRecyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(this)
                .paintProvider(this)
                .visibilityProvider(this)
                .marginProvider(this)
                .build())
    }

    abstract fun getVHTypes(): List<IVHType?>

    override fun dividerPaint(position: Int, parent: RecyclerView?): Paint {
        val paint = Paint()
        paint.setColor(Color.LTGRAY)
        paint.setStrokeWidth(0.5f.dpToPx())
        return paint
    }

    override fun dividerLeftMargin(position: Int, parent: RecyclerView?): Int {
        return 10.dpToPx()
    }

    override fun dividerRightMargin(position: Int, parent: RecyclerView?): Int {
        return 10.dpToPx()
    }

    override fun shouldHideDivider(position: Int, parent: RecyclerView?): Boolean {
        if (position == 0)
            return true
        else
            return false
    }
}