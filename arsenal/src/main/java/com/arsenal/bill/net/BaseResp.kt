package com.arsenal.bill.net

import com.google.gson.JsonObject

open class BaseResp {
    var code: Int = -1
    var json: JsonObject? = null
}