package com.bill.billdemo.page

import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.page.ArsenalBaseActivity
import com.arsenal.bill.recyclerview.BaseListAuth
import com.arsenal.bill.util.RouterUtil
import com.arsenal.bill.util.setApi
import com.arsenal.bill.util.setAuth
import com.arsenal.bill.util.setVHTypes
import com.bill.billdemo.R
import com.bill.billdemo.entity.VHType
import com.bill.billdemo.net.RequestInfo

@Route(path = RouterUtil.PAGE_BASE_LIST_ACTIVITY)
class BaseListActivity : BaseActivity() {

    @Autowired(name = RouterUtil.VALUE_VH_TYPES)
    @JvmField
    var vh_types: Array<VHType>? = null

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
        val result = ARouter.getInstance().build(RouterUtil.PAGE_BASE_LIST_FRAGMENT)
                .setVHTypes(VHType.COMMUNITY_TYPE)
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