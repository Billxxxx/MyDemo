package com.bill.billdemo.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.recyclerview.BaseBindingVH
import com.bill.billdemo.R
import com.bill.billdemo.databinding.VhPostArticleBinding
import com.bill.billdemo.entity.PostBean

class PostArticleVH(i: LayoutInflater, p: ViewGroup)
    : BaseBindingVH<VhPostArticleBinding, PostBean>(R.layout.vh_post_article, i, p) {
    init {
        itemView.setOnClickListener {

        }
    }

    override fun setData(data: PostBean) {
        super.setData(data)
        binding.postBean = data
    }
}