package com.bill.billdemo.entity

import com.chad.library.adapter.base.entity.MultiItemEntity

class UserBean : MultiItemEntity {
    override fun getItemType(): Int {
        return ViewHolderType.USER_TYPE.itemType
    }

    var name: String? = null
    var account: String? = null
    var bgLogo: String? = null
    var company: String? = null
    var authority: Int? = null
}