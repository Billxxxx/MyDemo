package com.bill.billdemo.entity

import android.text.TextUtils

class UserBean {

    var name: String? = null
    var account: String? = null
    var bgLogo: String? = null
    var company: String? = null
    var authority: Int? = null
    var rName: String? = null
    var bIcon: String? = null

    val isGossip: Boolean
        get() = TextUtils.isEmpty(rName)
    var realIcon: String? = null

    var icon: String?
        get() = if (isGossip)
            bIcon
        else
            realIcon
        set(icon) {
            this.realIcon = icon
        }
}