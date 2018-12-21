package com.bill.billdemo.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.recyclerview.BaseListAuth
import com.arsenal.bill.recyclerview.IVHType
import com.arsenal.bill.util.RouterUtil
import com.arsenal.bill.util.setApi
import com.arsenal.bill.util.setAuth
import com.arsenal.bill.util.setVHTypeArray
import com.bill.billdemo.R
import com.bill.billdemo.net.RequestInfo
import com.bill.billdemo.page.PageUtil.Companion.BASE_LIST_FRAGMENT_ACTIVITY

@Route(path = BASE_LIST_FRAGMENT_ACTIVITY)
class TempActivity : FragmentActivity() {
    @Autowired(name = RouterUtil.VALUE_VH_TYPES)
    @JvmField
    var vh_types: Array<IVHType>? = null


    @Autowired(name = RouterUtil.VALUE_API_INFO)
    @JvmField
    var resp: RequestInfo? = null

    @Autowired(name = RouterUtil.VALUE_PAGE_AUTH)
    @JvmField
    var list_page_auth: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_fragment)
        val result = ARouter.getInstance().build(PageUtil.BASE_LIST_FRAGMENT)
                .setVHTypeArray(vh_types)
                .setApi(resp)
                .setAuth(BaseListAuth.DISABLE_PULL_TO_REFRESH)
                .navigation();
        if (result is Fragment)
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_base_layout, result)
                commit()
            }
    }
}