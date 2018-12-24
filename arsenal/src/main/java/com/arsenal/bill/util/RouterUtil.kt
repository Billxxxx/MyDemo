package com.arsenal.bill.util

import com.alibaba.android.arouter.facade.Postcard
import com.arsenal.bill.recyclerview.BaseListAuth
import com.arsenal.bill.recyclerview.IVHType
import com.arsenal.bill.retrofit.BaseRequestInfo
import com.arsenal.bill.util.RouterUtil.Companion.PAGE_BASE_LIST_ACTIVITY
import com.arsenal.bill.util.RouterUtil.Companion.PAGE_BASE_LIST_FRAGMENT
import com.google.gson.Gson

/**跳转参数*/
class RouterUtil {
    companion object {
        const val VALUE_VH_TYPES = "vh_types"
        const val VALUE_API_INFO = "api_info"
        const val VALUE_PAGE_AUTH = "page_auth"
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

fun getCommonListURL(target: String, auths: Array<BaseListAuth>, vhTypes: Array<IVHType?>?, api: BaseRequestInfo): String {
    val gson = Gson()
    var url = "r://a.b" + target + "?"
    url += RouterUtil.VALUE_VH_TYPES + "=" + gson.toJson(vhTypes)
    var totalAuth = 0
    auths.forEach {
        totalAuth += it.authInt
    }
    url += "&" + RouterUtil.VALUE_API_INFO + "=" + gson.toJson(api)
    url += "&" + RouterUtil.VALUE_PAGE_AUTH + "=" + gson.toJson(totalAuth)
    return url
}

fun getCommonListFragmentURL(auths: Array<BaseListAuth>, vhTypes: Array<IVHType?>?, api: BaseRequestInfo): String {
    return getCommonListURL(PAGE_BASE_LIST_FRAGMENT, auths, vhTypes, api)
}

fun getCommonListActivityURL(auths: Array<BaseListAuth>, vhTypes: Array<IVHType?>?, api: BaseRequestInfo): String {
    return getCommonListURL(PAGE_BASE_LIST_ACTIVITY, auths, vhTypes, api)
}

fun getCommonListFragmentURL(auths: BaseListAuth, vhTypes: IVHType?, api: BaseRequestInfo): String {
    return getCommonListFragmentURL(arrayOf(auths), arrayOf(vhTypes), api)
}

fun getCommonListActivityURL(auths: BaseListAuth, vhTypes: IVHType?, api: BaseRequestInfo): String {
    return getCommonListActivityURL(arrayOf(auths), arrayOf(vhTypes), api)
}
