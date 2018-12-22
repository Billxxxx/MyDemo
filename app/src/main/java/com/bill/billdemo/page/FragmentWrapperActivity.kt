package com.bill.billdemo.page

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Base64
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.activity.ArsenalBaseActivity
import com.arsenal.bill.recyclerview.BaseListAuth
import com.arsenal.bill.util.RouterUtil
import com.arsenal.bill.util.setApi
import com.arsenal.bill.util.setAuth
import com.arsenal.bill.util.setVHTypes
import com.bill.billdemo.R
import com.bill.billdemo.entity.ViewHolderType
import com.bill.billdemo.net.RequestInfo

@Route(path = RouterUtil.PAGE_BASE_LIST_ACTIVITY)
class FragmentWrapperActivity : ArsenalBaseActivity() {

//    @Autowired(name = RouterUtil.VALUE_VH_TYPES)
//    @JvmField
//    var vh_types: Array<ViewHolderType>? = null
//
//
//    @Autowired(name = RouterUtil.VALUE_API_INFO)
//    @JvmField
//    var resp: RequestInfo? = null
//
//    @Autowired(name = RouterUtil.VALUE_PAGE_AUTH)
//    @JvmField
//    var list_page_auth: Int = 0

    @Autowired(name = "url")
    @JvmField
    var url: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_fragment)
        if (!TextUtils.isEmpty(url)) {
            val value = String(Base64.decode(url, Base64.DEFAULT))
            val result = ARouter.getInstance().build(Uri.parse(value)).navigation()
//        val result = ARouter.getInstance().build(RouterUtil.PAGE_BASE_LIST_FRAGMENT)
//                .setVHTypes(ViewHolderType.COMMUNITY_TYPE)
//                .setApi(resp)
//                .setAuth(BaseListAuth.DISABLE_PULL_TO_REFRESH)
//                .navigation();
            if (result is Fragment)
                supportFragmentManager.beginTransaction().apply {
                    add(R.id.fragment_base_layout, result)
                    commit()
                }
        }
    }
}