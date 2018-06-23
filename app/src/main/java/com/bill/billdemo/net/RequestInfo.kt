package com.bill.billdemo.net

import com.arsenal.bill.net.IResp
import com.arsenal.bill.retrofit.BaseRequestInfo
import com.bill.billdemo.entity.CommunityListResp
import com.bill.billdemo.entity.RecommendExpertListResp

enum class RequestInfo(var mCommand: String, var mClazz: Class<out IResp>) : BaseRequestInfo {
    Expert_List("v3_app_index_hj", RecommendExpertListResp::class.java),
    Community_List("v3_communityList", CommunityListResp::class.java),
    ;

    override fun getCommand(): String {
        return mCommand
    }

    override fun getClazz(): Class<out IResp> {
        return mClazz
    }
}