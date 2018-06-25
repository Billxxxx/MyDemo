package com.bill.billdemo.entity

import com.chad.library.adapter.base.entity.MultiItemEntity

class CommunityBean : MultiItemEntity {
    var children: ArrayList<CommunityGroupBean>? = null

    override fun getItemType(): Int {
        return ViewHolderType.COMMUNITY_TYPE.itemType
    }

    var name: String? = null
    var logo: String? = null
    var id: String? = null
}