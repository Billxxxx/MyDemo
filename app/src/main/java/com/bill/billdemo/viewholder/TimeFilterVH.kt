package com.bill.billdemo.viewholder

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.recyclerview.BaseBindingVH
import com.bill.billdemo.R
import com.bill.billdemo.databinding.VhTimeFilterBinding
import com.bill.billdemo.entity.HomeTimeBean

class TimeFilterVH(
        i: LayoutInflater,
        p: ViewGroup)
    : BaseBindingVH<VhTimeFilterBinding, HomeTimeBean>(DataBindingUtil.inflate(i, R.layout.vh_time_filter, p, false)) {
    init {

    }

    override fun setData(data: HomeTimeBean) {
        super.setData(data)
        binding.timeBean = data
    }
}