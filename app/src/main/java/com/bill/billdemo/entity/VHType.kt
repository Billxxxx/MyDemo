package com.bill.billdemo.entity

import com.bill.billdemo.viewholder.ImageTextVH
import com.bill.billdemo.viewholder.ImageVH
import com.bill.billdemo.R
import com.bill.billdemo.viewholder.TextVH
import com.bill.billdemo.viewholder.BaseVH
import com.bill.billdemo.viewholder.DefaultVH
import com.chad.library.adapter.base.entity.MultiItemEntity

enum class VHType(c: Class<out BaseVH>, var layoutId: Int, var sameVHType: VHType? = null) : MultiItemEntity {
    DEFAULT(DefaultVH::class.java, 0),
    TEXT(TextVH::class.java, R.layout.item_text_view),
    IMAGE(ImageVH::class.java, R.layout.item_image_view),
    IMAGE_TEXT(ImageTextVH::class.java, R.layout.item_img_text_view),
    ;

    override fun getItemType(): Int {
        //相同数据类型返回同一个type
        if (sameVHType != null)
            return sameVHType!!.ordinal
        else
            return ordinal
    }

    fun setData(data: Any) {

    }
}