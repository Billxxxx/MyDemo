package com.bill.billdemo.activity

class RequestBaseJson(
        @Transient
        var data: HashMap<String, String>,
        var message: String = "")