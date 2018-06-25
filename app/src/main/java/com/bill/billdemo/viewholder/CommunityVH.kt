package com.bill.billdemo.viewholder

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.recyclerview.BaseDataBindingVH
import com.bill.billdemo.R
import com.bill.billdemo.databinding.VhCommunityBinding
import com.bill.billdemo.entity.CommunityBean

class CommunityVH(
        i: LayoutInflater,
        p: ViewGroup)
    : BaseDataBindingVH<VhCommunityBinding, CommunityBean>(DataBindingUtil.inflate(i, R.layout.vh_community, p, false)) {
    init {
        itemView.setOnClickListener {

        }
    }

    override fun setData(data: CommunityBean) {
        super.setData(data)
        dataBinding.community = data
        itemView.icon_iv.loadUrl(data.logo)
    }
}