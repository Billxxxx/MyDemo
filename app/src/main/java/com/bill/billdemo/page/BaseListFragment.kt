package com.bill.billdemo.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.activity.ArsenalListFragment
import com.arsenal.bill.entity.ListDividerBean
import com.arsenal.bill.recyclerview.IVHType
import com.arsenal.bill.retrofit.BaseRequestInfo
import com.bill.billdemo.entity.ViewHolderType
import com.bill.billdemo.net.RequestInfo

/**
 * Created by yu on 2016/11/11.
 */

@Route(path = "/bill/base_list_fragment")
class BaseListFragment : ArsenalListFragment() {

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

    @Autowired(name = "dividerBean")
    @JvmField
    var dividerBean: ListDividerBean? = null

    override fun getListDividerBean(): ListDividerBean {
        if (dividerBean == null)
            return super.getListDividerBean()
        else
            return dividerBean!!
    }

    override fun getRequestInfo(): BaseRequestInfo? {
        return resp
    }

    companion object {
        fun newInstance(bundle: Bundle): BaseListFragment {
            return BaseListFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (arguments != null) {
            ARouter.getInstance().inject(this)
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}