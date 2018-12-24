package com.bill.billdemo.entity

import com.arsenal.bill.entity.ListDividerBean
import com.bill.billdemo.net.RequestInfo

class BaseListFragmentConfig(
        /**页面的功能，比如下拉刷新，上拉加载*/
        var pageAuth: Int? = null,
        /**API接口*/
        var apiInfo: RequestInfo? = null,
        /**本页启用的VH类型*/
        var vhTypes: Array<VHType>? = null,
        /**分割线逻辑*/
        var listDividerBean: ListDividerBean? = null
)