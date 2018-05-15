package com.bill.billdemo.entity

import com.bill.billdemo.viewholder.*
import com.chad.library.adapter.base.entity.MultiItemEntity

enum class VHType(var vhClass: Class<out BaseVH>, var sameVHType: VHType? = null) : MultiItemEntity {
    DEFAULT(DefaultVH::class.java),
    TEXT(TextVH::class.java),
    IMAGE(ImageVH::class.java),
    IMAGE_TEXT(ImageTextVH::class.java),
    ;

    override fun getItemType(): Int {
        //相同数据类型返回同一个type
        if (sameVHType != null)
            return sameVHType!!.ordinal
        else
            return ordinal
    }
}