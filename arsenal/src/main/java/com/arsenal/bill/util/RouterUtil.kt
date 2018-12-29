package com.arsenal.bill.util

import com.alibaba.android.arouter.facade.Postcard
import com.arsenal.bill.entity.ListDividerBean
import com.arsenal.bill.recyclerview.BaseListAuth
import com.arsenal.bill.recyclerview.IVHType
import com.arsenal.bill.retrofit.BaseRequestInfo

/**跳转参数*/
class RouterUtil {
    companion object {
        const val VALUE_VH_TYPES = "vh_types"
        const val VALUE_API_INFO = "api_info"
        const val VALUE_PAGE_AUTH = "page_auth"
        const val VALUE_OFFSCREEN_PAGE_LIMIT = "offscreen_page_limit"
        const val VALUE_INDEX = "index"
        const val VALUE_NAMES = "names"
        const val VALUE_FRAGMENT_DATA = "fragment_data"
        const val VALUE_INDICATOR_TYPE = "indicator_type"
        const val VALUE_BASE_LIST_CONFIG = "base_list_config"
        const val VIEWPAGER_FRAGMENTS_URL = "viewpager_fragments_url"
        const val PAGE_BASE_LIST_ACTIVITY = "/bill/activity_base_list"
        const val PAGE_BASE_LIST_FRAGMENT = "/bill/fragment_base_list"
    }
}

fun Postcard.setVHTypes(vararg vhTypes: IVHType?): Postcard {
    withObject(RouterUtil.VALUE_VH_TYPES, vhTypes)
    return this
}

fun Postcard.setVHTypeArray(vhTypes: Array<IVHType>?): Postcard {
    if (vhTypes != null)
        withObject(RouterUtil.VALUE_VH_TYPES, vhTypes)
    return this
}

fun Postcard.setApi(apiInfo: BaseRequestInfo?): Postcard {
    if (apiInfo != null)
        withObject(RouterUtil.VALUE_API_INFO, apiInfo)
    return this
}

fun Postcard.setAuth(vararg auths: BaseListAuth): Postcard {
    var totalAuth = 0
    auths.forEach {
        totalAuth += it.authInt
    }
    setAuth(totalAuth)
    return this
}

fun Postcard.setAuth(totalAuth: Int): Postcard {
    withInt(RouterUtil.VALUE_PAGE_AUTH, totalAuth)
    return this
}
