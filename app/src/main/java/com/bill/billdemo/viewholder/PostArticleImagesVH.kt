package com.bill.billdemo.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.recyclerview.BaseBindingVH
import com.bill.billdemo.R
import com.bill.billdemo.databinding.VhPostArticleImagesBinding
import com.bill.billdemo.entity.PostBean

class PostArticleImagesVH(i: LayoutInflater, p: ViewGroup)
    : BaseBindingVH<VhPostArticleImagesBinding, PostBean>(R.layout.vh_post_article_images, i, p) {
    init {
        itemView.setOnClickListener {

        }
    }

    override fun setData(data: PostBean) {
        super.setData(data)
        binding.postBean = data
    }
}