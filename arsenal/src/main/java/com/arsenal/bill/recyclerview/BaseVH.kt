package com.arsenal.bill.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseViewHolder

open class BaseVH<T>(view: View) : BaseViewHolder(view) {

    constructor(l: Int, i: LayoutInflater, p: ViewGroup) : this(i.inflate(l, p, false))

    open fun setData(data: T) {}
}