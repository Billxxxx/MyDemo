package com.bill.billdemo.page

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.arsenal.bill.activity.ArsenalListActivity
import com.arsenal.bill.recyclerview.IVHType
import com.arsenal.bill.retrofit.BaseRequestInfo
import com.arsenal.bill.util.Constant.Companion.VH_TYPES
import com.bill.billdemo.entity.ViewHolderType
import com.bill.billdemo.net.RequestInfo

@Route(path = "/bill/base_list_activity")
class BaseListActivity : ArsenalListActivity() {

    @Autowired(name = VH_TYPES)
    @JvmField
    var vh_types: ViewHolderType? = null

    override fun getVHTypes(): List<IVHType?> {
        return listOf(vh_types)
    }

    @Autowired(name = "resp")
    @JvmField
    var resp: RequestInfo? = null

    override fun getRequestInfo(): BaseRequestInfo? {
        return resp
    }

    @Autowired(name = "list_page_auth")
    @JvmField
    var list_page_auth: Int = 0

    override fun getListPageAuthority(): Int {
        return list_page_auth
    }
}