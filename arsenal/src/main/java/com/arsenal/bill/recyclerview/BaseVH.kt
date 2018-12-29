package com.arsenal.bill.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsenal.bill.R
import com.arsenal.bill.net.VHItemEntity
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity

open class BaseVH<T>(view: View) : BaseViewHolder(view) {

    constructor(l: Int, i: LayoutInflater, p: ViewGroup) : this(i.inflate(l, p, false))

    open fun setData(data: T) {}
}

class DefaultVH(i: LayoutInflater) : BaseVH<VHItemEntity>(i.inflate(R.layout.vh_default, null)) {}