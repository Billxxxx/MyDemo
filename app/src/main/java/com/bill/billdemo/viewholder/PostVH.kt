package com.bill.billdemo.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.recyclerview.BaseBindingVH
import com.bill.billdemo.R
import com.bill.billdemo.databinding.VhPostBinding
import com.bill.billdemo.entity.PostBean
import kotlinx.android.synthetic.main.vh_post.view.*

class PostVH(i: LayoutInflater, p: ViewGroup)
    : BaseBindingVH<VhPostBinding, PostBean>(R.layout.vh_post, i, p) {
    init {
        itemView.setOnClickListener {

        }
    }

    override fun setData(data: PostBean) {
        super.setData(data)
        binding.postBean = data
        itemView.title_tv.text = data.title
    }
}