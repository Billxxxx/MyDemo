package com.bill.billdemo.page

import com.alibaba.android.arouter.facade.Postcard
import com.arsenal.bill.entity.ListDividerBean
import com.arsenal.bill.util.RouterUtil
import com.bill.billdemo.entity.BaseListFragmentConfig
import com.bill.billdemo.entity.VHType
import com.bill.billdemo.net.RequestInfo

class ARouterPageUtil {
    companion object {
        const val PAGE_VIEW_PAGER_ACTIVITY = "/bill/activity_view_pager"
        const val PAGE_VIEW_PAGER_FRAGMENT = "/bill/fragment_view_pager"
        const val PAGE_MINE_FRAGMENT = "/bill/fragment_mine"
        const val PAGE_MAIN_ACTIVITY = "/bill/activity_main"
        const val PAGE_MAIN_FRAGMENT = "/bill/fragment_main"
    }
}

fun Postcard.putListConfig(api: RequestInfo, pageAuth: Int? = null, listDividerBean: ListDividerBean? = null, vhTypes: Array<VHType>): Postcard {
    withObject(RouterUtil.VALUE_BASE_LIST_CONFIG, BaseListFragmentConfig(pageAuth, api, vhTypes, listDividerBean))
    return this
}