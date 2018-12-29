package com.bill.billdemo.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.recyclerview.BaseBindingVH
import com.bill.billdemo.R
import com.bill.billdemo.databinding.VhTabloidItemBinding
import com.bill.billdemo.net.resp.NewsItemBean

class NewsVH(i: LayoutInflater, p: ViewGroup)
    : BaseBindingVH<VhTabloidItemBinding, NewsItemBean>(R.layout.vh_tabloid_item, i, p) {
    init {
        itemView.setOnClickListener {

        }
    }

    override fun setData(data: NewsItemBean) {
        super.setData(data)
        binding.newsItem = data
    }
}