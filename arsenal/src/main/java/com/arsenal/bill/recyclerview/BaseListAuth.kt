package com.arsenal.bill.recyclerview

import java.io.Serializable


enum class BaseListAuth : Serializable {
    /**是否禁用下拉刷新，默认true*/
    DISABLE_PULL_TO_REFRESH,

    /**是否禁用加载更多*/
    DISABLE_MORE,

    /**是否禁用分割线*/
    DISABLE_DIVIDER,

    /**是否启用刷新上一页面*/
    ENABLE_BACK_REFRESH,

    /**是否禁用自动刷新*/
    DISABLE_AUTO_REFRESH,

    /**是否自动初始化*/
    DISABLE_AUTO_INIT,
    ;

    var authInt: Int

    init {
        authInt = 1 shl ordinal
    }
}