package com.bill.billdemo.viewholder

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.recyclerview.BaseBindingVH
import com.arsenal.bill.util.loadUrl
import com.bill.billdemo.R
import com.bill.billdemo.databinding.VhCommunityBinding
import com.bill.billdemo.entity.CommunityBean
import kotlinx.android.synthetic.main.vh_community.view.*

class CommunityVH(
        i: LayoutInflater,
        p: ViewGroup)
    : BaseBindingVH<VhCommunityBinding, CommunityBean>(DataBindingUtil.inflate(i, R.layout.vh_community, p, false)) {
    init {
        itemView.setOnClickListener {

        }
    }

    override fun setData(data: CommunityBean) {
        super.setData(data)
        binding.community = data
        itemView.icon_iv.loadUrl(data.logo)
    }
}