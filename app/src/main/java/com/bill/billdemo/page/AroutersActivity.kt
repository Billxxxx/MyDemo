package com.bill.billdemo.page

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.recyclerview.BaseListAuth
import com.arsenal.bill.util.RouterUtil
import com.arsenal.bill.util.RouterUtil.Companion.PAGE_BASE_LIST_ACTIVITY
import com.arsenal.bill.util.getCommonListActivityURL
import com.arsenal.bill.util.getCommonListFragmentURL
import com.bill.billdemo.BuildConfig
import com.bill.billdemo.R
import com.bill.billdemo.entity.ViewHolderType
import com.bill.billdemo.net.RequestInfo
import com.bill.billdemo.page.ARouterPageUtil.Companion.PAGE_VIEW_PAGER_ACTIVITY
import com.google.gson.Gson
import kotlinx.android.synthetic.main.ac_main.*

class AroutersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main)

        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }

        sample_list_btn.setOnClickListener {
            val urls = arrayOf(
                    getCommonListFragmentURL(BaseListAuth.DISABLE_PULL_TO_REFRESH, ViewHolderType.COMMUNITY_TYPE, RequestInfo.V3_COMMUNITY_LIST),
                    getCommonListFragmentURL(BaseListAuth.DISABLE_PULL_TO_REFRESH, ViewHolderType.COMMUNITY_TYPE, RequestInfo.V3_COMMUNITY_LIST),
                    getCommonListFragmentURL(BaseListAuth.DISABLE_PULL_TO_REFRESH, ViewHolderType.COMMUNITY_TYPE, RequestInfo.V3_COMMUNITY_LIST)
            )
            val testUriMix = Uri.parse("r://a.b" + PAGE_VIEW_PAGER_ACTIVITY + "?urls=" + Gson().toJson(urls))
            ARouter.getInstance().build(testUriMix).navigation();
        }
        base_list_btn.setOnClickListener {
            try {
//                var gson = Gson()
//                var url = "r://a.b" + RouterUtil.PAGE_BASE_LIST_ACTIVITY + "?"
//                url += RouterUtil.VALUE_VH_TYPES + "=" + gson.toJson(arrayOf(ViewHolderType.COMMUNITY_TYPE))
//                url += "&" + RouterUtil.VALUE_API_INFO + "=" + gson.toJson(RequestInfo.V3_COMMUNITY_LIST)
//                url += "&" + RouterUtil.VALUE_PAGE_AUTH + "=" + BaseListAuth.DISABLE_PULL_TO_REFRESH.authInt
                var url = getCommonListFragmentURL(BaseListAuth.DISABLE_PULL_TO_REFRESH, ViewHolderType.COMMUNITY_TYPE, RequestInfo.V3_COMMUNITY_LIST)
                url = Base64.encodeToString(url.toByteArray(), Base64.DEFAULT)
                url = "r://a.b" + PAGE_BASE_LIST_ACTIVITY + "?url=" + url
                ARouter.getInstance().build(Uri.parse(url)).navigation();
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        retrofit_tv.setOnClickListener {
            ARouter.getInstance().build("/bill/retrofit").navigation();
        }
    }
}