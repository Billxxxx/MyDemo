package com.bill.billdemo.entity

import com.arsenal.bill.recyclerview.BaseVH
import com.arsenal.bill.recyclerview.IVHType
import com.bill.billdemo.R
import com.bill.billdemo.viewholder.CommunityVH
import com.bill.billdemo.viewholder.ImageTextVH
import com.bill.billdemo.viewholder.UserVH
import com.chad.library.adapter.base.entity.MultiItemEntity

enum class ViewHolderType(var vhClass: Class<out BaseVH<out MultiItemEntity>>, var sameVHType: ViewHolderType? = null)
    : MultiItemEntity, IVHType {
    DEFAULT(R.layout.vh_default),
    TEXT(R.layout.vh_text_view),
    IMAGE(R.layout.item_image_view),
    IMAGE_TEXT(ImageTextVH::class.java),
    USER_TYPE(UserVH::class.java),
    COMMUNITY_TYPE(CommunityVH::class.java)
    ;

    var layout: Int = -1

    /**
     * 简单的ViewHolder 之传入布局进行初始化
     */
    constructor(layout: Int, sameVHType: ViewHolderType? = null) {
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

    override fun getVHClass(): Class<out BaseVH<out MultiItemEntity>> {
        return vhClass
    }

    /**
     * 简单的ViewHolder（指不需要在ViewHolder中使用内部对象）,在enum初始化时传入布局
     */
    override fun getLayoutId(): Int {
        return layout
    }
}