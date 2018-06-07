package com.arsenal.bill.entity

import com.arsenal.bill.BaseVH
import com.chad.library.adapter.base.entity.MultiItemEntity

interface IVHType {
    fun getItemType(): Int
    fun getVHClass(): Class<out BaseVH<out MultiItemEntity>>
    fun getLayoutId(): Int
}
