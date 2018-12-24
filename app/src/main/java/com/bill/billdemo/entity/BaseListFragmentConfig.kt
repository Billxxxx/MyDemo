package com.bill.billdemo.entity

import com.arsenal.bill.entity.ListDividerBean
import com.bill.billdemo.net.RequestInfo

class BaseListFragmentConfig(
        var pageAuth: Int? = null,
        var apiInfo: RequestInfo? = null,
        var vhTypes: Array<ViewHolderType>? = null,
        var listDividerBean: ListDividerBean? = null
)