package com.arsenal.bill

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseViewHolder

open class BaseVH(layoutId: Int, i: LayoutInflater, p: ViewGroup) : BaseViewHolder(i.inflate(layoutId, p, false)) {
    open fun setData(data: Any) {}
}