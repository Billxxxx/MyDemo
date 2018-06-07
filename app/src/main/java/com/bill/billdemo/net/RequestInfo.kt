package com.bill.billdemo.net

import com.bill.billdemo.entity.RecommendExpertListResp

enum class RequestInfo(var command: String, var clazz: Class<out IResp>) {
    Expert_List("v3_app_index_hj", RecommendExpertListResp::class.java)
    ;
}