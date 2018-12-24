package com.bill.billdemo.viewholder

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.recyclerview.BaseDataBindingVH
import com.bill.billdemo.R
import com.bill.billdemo.databinding.VhCommunityBinding
import com.bill.billdemo.entity.HomeTimeBean

class TimeFilterVH(
        i: LayoutInflater,
        p: ViewGroup)
    : BaseDataBindingVH<VhCommunityBinding, HomeTimeBean>(DataBindingUtil.inflate(i, R.layout.vh_time_filter, p, false)) {
    init {
        itemView.setOnClickListener {

        }
    }

    override fun setData(data: HomeTimeBean) {
        super.setData(data)
    }
}