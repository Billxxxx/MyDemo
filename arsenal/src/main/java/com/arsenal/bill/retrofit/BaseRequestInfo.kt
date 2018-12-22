package com.arsenal.bill.retrofit

import com.arsenal.bill.net.IResp

interface BaseRequestInfo {
    fun getApiCommand(): String?
    fun getApiClazz(): Class<out IResp>?
}
