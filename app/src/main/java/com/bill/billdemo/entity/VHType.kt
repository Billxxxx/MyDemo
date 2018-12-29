package com.bill.billdemo.entity

import com.arsenal.bill.recyclerview.BaseVH
import com.arsenal.bill.recyclerview.IVHType
import com.bill.billdemo.R
import com.bill.billdemo.viewholder.*
import com.chad.library.adapter.base.entity.MultiItemEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class VHType(
        var vhClass: Class<out BaseVH<out MultiItemEntity>>,
        var sameVHType: VHType? = null)
    : MultiItemEntity, IVHType {
    DEFAULT(R.layout.vh_default),
    /**圈子详情*/
    COMMUNITY_TYPE(CommunityVH::class.java),
    /**时间*/
    TIME_FILTER(TimeFilterVH::class.java),
    /**新闻*/
    NEWS(NewsVH::class.java),
    POST(PostVH::class.java),
    /**阅读的banner广告*/
    ReadHead(ReadHeadVH::class.java),
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