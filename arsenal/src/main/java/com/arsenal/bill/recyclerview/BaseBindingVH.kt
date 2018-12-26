package com.arsenal.bill.recyclerview

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup

open class BaseBindingVH<B : ViewDataBinding, T>(
        var binding: B) : BaseVH<T>(binding.root) {
    constructor(layoutID: Int,
                inflater: LayoutInflater,
                parent: ViewGroup) : this(DataBindingUtil.inflate(inflater, layoutID, parent, false))
}