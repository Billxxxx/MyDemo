package com.arsenal.bill.retrofit

import com.arsenal.bill.net.IResp

interface BaseRequestInfo{
    fun getCommand():String
    fun getClazz():Class<out IResp>
}