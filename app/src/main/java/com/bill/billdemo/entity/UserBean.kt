package com.bill.billdemo.entity

import com.chad.library.adapter.base.entity.MultiItemEntity

class UserBean : MultiItemEntity {
    override fun getItemType(): Int {
        return VHType.USER_TYPE.itemType
    }

    var name: String? = null
}