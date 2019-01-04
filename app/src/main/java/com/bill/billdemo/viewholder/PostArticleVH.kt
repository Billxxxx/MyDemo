package com.bill.billdemo.viewholder

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.arsenal.bill.recyclerview.BaseBindingVH
import com.arsenal.bill.util.setLeftTagText
import com.bill.billdemo.R
import com.bill.billdemo.databinding.VhPostArticleBinding
import com.bill.billdemo.entity.PostBean
import com.bill.billdemo.entity.PostType
import kotlinx.android.synthetic.main.include_post_title_content.view.*

class PostArticleVH(i: LayoutInflater, p: ViewGroup)
    : BaseBindingVH<VhPostArticleBinding, PostBean>(R.layout.vh_post_article, i, p) {
    init {
        itemView.setOnClickListener {

        }
    }

    override fun setData(data: PostBean) {
        super.setData(data)
        binding.postBean = data
        val title = data.title
        if (!TextUtils.isEmpty(title))
            setQATitleWithTag(data.type, itemView.title_tv, title!!)

    }

    fun setQATitleWithTag(type: Int, textView: TextView, text: String) {
        if (type == PostType.QA.ordinal) {
            textView.setLeftTagText("问", R.color.encourage_pay_dialog, text)
        } else if (type == PostType.VIDEO.ordinal) {
            textView.setLeftTagText("视", R.color.video_tag_background, text)
        } else {
            textView.text = text
        }
    }
}