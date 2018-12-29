package com.arsenal.bill.views

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.arsenal.bill.util.createView

abstract class SimpleGridRecyclerView<T>(var mContext: Context, var widthLimit: Int, var itemLayoutId: Int, var mData: List<T>? = null) {
    var mRootView: LinearLayout

    abstract fun setData(view: View, bean: T, position: Int)

    init {
        mRootView = LinearLayout(mContext).apply {
            orientation = LinearLayout.VERTICAL
        }
    }

    fun setData(data: List<T>?) {
        mData = data
        if (data == null)
            return
        var layout: LinearLayout? = null
        if (mRootView.childCount == 0) {
            val forTime = data.size / widthLimit
            for (i in 0..if (data.size % widthLimit > 0) forTime + 1 else forTime)
                for (j in 0..widthLimit - 1) {
                    val position = i * 5 + j
                    if (j == 0) {
                        layout = LinearLayout(mContext).apply {
                            orientation = LinearLayout.HORIZONTAL
                        }
                        mRootView.addView(layout)
                    }
                    if (data.size > position) {
                        layout?.addView(mContext.createView(itemLayoutId)?.apply {
                            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                                weight = 1f
                            }
                            setData(this, data[position], position)
                        })
                    } else {
                        layout?.addView(View(mContext).apply {
                            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                                weight = 1f
                            }
                        })
                    }
                }
        }
        Log.d("bannerTest", "setCommunity end")
    }

    fun getCount(): Int {
        return if (mData == null) 0 else mData!!.size
    }
}