package com.arsenal.bill.entity

import com.chad.library.adapter.base.entity.MultiItemEntity

class MultipleItem(var type: IVHType, var spanSize: Int = 0, var content: String? = null) : MultiItemEntity {
    override fun getItemType(): Int {
        return type.getItemType()
    }
}
