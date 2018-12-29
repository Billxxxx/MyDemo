package com.bill.billdemo.entity

import com.arsenal.bill.net.VHItemEntity
import com.arsenal.bill.recyclerview.IVHType

class CommunityBean : VHItemEntity() {
    override fun getVHType(): IVHType {
        return VHType.COMMUNITY_TYPE
    }

    var name: String? = null
    var logo: String? = null
    var id: String? = null
}

class TopTopic {
    var title: String? = null
}