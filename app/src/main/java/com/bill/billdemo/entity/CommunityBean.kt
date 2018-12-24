package com.bill.billdemo.entity

import com.chad.library.adapter.base.entity.MultiItemEntity

class CommunityBean : MultiItemEntity {
    var children: ArrayList<CommunityGroupBean>? = null

    override fun getItemType(): Int {
        return VHType.COMMUNITY_TYPE.itemType
    }

    var name: String? = null
    var logo: String? = null
    var id: String? = null
    var topTopic: TopTopic? = null
}

class TopTopic {
    var title: String? = null
}