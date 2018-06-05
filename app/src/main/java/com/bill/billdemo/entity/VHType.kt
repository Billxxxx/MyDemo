package com.bill.billdemo.entity

import com.arsenal.bill.BaseVH
import com.arsenal.bill.entity.IVHType
import com.bill.billdemo.R
import com.bill.billdemo.viewholder.ImageTextVH
import com.bill.billdemo.viewholder.UserVH
import com.chad.library.adapter.base.entity.MultiItemEntity

enum class VHType(var vhClass: Class<out BaseVH>, var sameVHType: VHType? = null)
    : MultiItemEntity, IVHType {
    DEFAULT(R.layout.vh_default),
    TEXT(R.layout.vh_text_view),
    IMAGE(R.layout.item_image_view),
    IMAGE_TEXT(ImageTextVH::class.java),
    USER_TYPE(UserVH::class.java)
    ;

    var layout: Int = -1

    /**
     * 简单的ViewHolder 之传入布局进行初始化
     */
    constructor(layout: Int, sameVHType: VHType? = null) {
        this.layout = layout
        this.sameVHType = sameVHType
    }

    override fun getItemType(): Int {
        //相同数据类型返回同一个type
        if (sameVHType != null)
            return sameVHType!!.ordinal
        else
            return ordinal
    }

    override fun getVHClass(): Class<out BaseVH> {
        return vhClass
    }

    /**
     * 简单的ViewHolder（指不需要在ViewHolder中使用内部对象）,在enum初始化时传入布局
     */
    override fun getLayoutId(): Int {
        return layout
    }
}