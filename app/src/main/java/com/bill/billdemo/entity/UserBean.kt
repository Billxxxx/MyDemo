package com.bill.billdemo.entity

import com.arsenal.bill.net.VHItemEntity
import com.arsenal.bill.recyclerview.IVHType

class UserBean : VHItemEntity() {
    override fun getVHType(): IVHType {
        return VHType.USER_TYPE
    }

    var name: String? = null
    var account: String? = null
    var bgLogo: String? = null
    var company: String? = null
    var authority: Int? = null
}