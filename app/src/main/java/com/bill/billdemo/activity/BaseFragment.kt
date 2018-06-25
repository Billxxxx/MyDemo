package com.bill.billdemo.activity

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.arsenal.bill.activity.ArsenalListFragment
import com.arsenal.bill.recyclerview.IVHType
import com.arsenal.bill.retrofit.BaseRequestInfo
import com.bill.billdemo.entity.ViewHolderType
import com.bill.billdemo.net.RequestInfo

/**
 * Created by yu on 2016/11/11.
 */

@Route(path = "/bill/base_list_fragment")
class BaseFragment : ArsenalListFragment() {

    @Autowired(name = "vh_types")
    @JvmField
    var vh_types: ViewHolderType? = null

    override fun getVHTypes(): List<IVHType?> {
        return listOf(vh_types)
    }

    @Autowired(name = "list_page_auth")
    @JvmField
    var list_page_auth: Int = 0

    override fun getListPageAuthority(): Int {
        return list_page_auth
    }

    @Autowired(name = "resp")
    @JvmField
    var resp: RequestInfo? = null

    override fun getRequestInfo(): BaseRequestInfo? {
        return resp
    }
}