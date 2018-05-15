package com.bill.billdemo.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.BaseVH
import com.bill.billdemo.R

class ImageTextVH(i: LayoutInflater, p: ViewGroup) : BaseVH(R.layout.item_img_text_view, i, p) {
    override fun setData(data: Any) {
        super.setData(data)
        when (layoutPosition % 2) {
            0 -> setImageResource(R.id.iv, R.drawable.animation_img1)
            1 -> setImageResource(R.id.iv, R.drawable.animation_img2)
        }
    }
}