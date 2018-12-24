package com.bill.billdemo.viewholder

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.recyclerview.BaseDataBindingVH
import com.bill.billdemo.R
import com.bill.billdemo.databinding.VhCommunityBinding
import com.bill.billdemo.entity.NewsItemBean

class TabloidItemVH(
        i: LayoutInflater,
        p: ViewGroup)
    : BaseDataBindingVH<VhCommunityBinding, NewsItemBean>(DataBindingUtil.inflate(i, R.layout.vh_tabloid_item, p, false)) {
    init {
        itemView.setOnClickListener {

        }
    }

    override fun setData(data: NewsItemBean) {
        super.setData(data)
    }
}