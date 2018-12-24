package com.bill.billdemo.net

import com.arsenal.bill.net.IResp
import com.arsenal.bill.retrofit.BaseRequestInfo
import com.bill.billdemo.entity.CommunityListResp
import com.bill.billdemo.entity.RecommendExpertListResp
import com.bill.billdemo.entity.TabloidResp

enum class RequestInfo(var mCommand: String, var mClazz: Class<out IResp>) : BaseRequestInfo {
    V3_EXPERT_LIST("v3_app_index_hj", RecommendExpertListResp::class.java),
    V3_COMMUNITY_LIST("v3_communityList", CommunityListResp::class.java),
    V4_TABLOID("/news/loadNewsByPage", TabloidResp::class.java),
    ;

    override fun getApiClazz(): Class<out IResp> {
        return mClazz
    }

    override fun getApiCommand(): String {
        return mCommand
    }
}