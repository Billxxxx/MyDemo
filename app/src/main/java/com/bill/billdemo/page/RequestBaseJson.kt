package com.bill.billdemo.page

class RequestBaseJson(
        @Transient
        var data: HashMap<String, String>,
        var message: String = "")