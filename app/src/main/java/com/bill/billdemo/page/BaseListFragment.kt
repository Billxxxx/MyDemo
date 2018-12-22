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
import com.arsenal.bill.util.RouterUtil
import com.bill.billdemo.entity.ViewHolderType
import com.bill.billdemo.net.RequestInfo

/**通用的列表fragment，列表数据均由此列表实现，可以由Activity包装PAGE_BASE_LIST_ACTIVITY*/
@Route(path = RouterUtil.PAGE_BASE_LIST_FRAGMENT)
class BaseListFragment() : ArsenalListFragment() {

    /**当前页面中启用的view holder*/
    @Autowired(name = RouterUtil.VALUE_VH_TYPES)
    @JvmField
    var vh_types: Array<ViewHolderType>? = null

    /**请求接口*/
    @Autowired(name = RouterUtil.VALUE_API_INFO)
    @JvmField
    var resp: RequestInfo? = null

    /**页面的功能，比如下拉刷新，上拉加载*/
    @Autowired(name = RouterUtil.VALUE_PAGE_AUTH)
    @JvmField
    var list_page_auth: Int = 0

    /**分割线逻辑*/
    @Autowired(name = "dividerBean")
    @JvmField
    var dividerBean: ListDividerBean? = null

    override fun getVHTypes(): List<IVHType?>? {
        return ArrayList<IVHType>().apply {
            vh_types?.forEach {
                this.add(it)
            }
        }
    }

    override fun getRequestInfo(): BaseRequestInfo? {
        return resp
    }

    override fun getListPageAuthority(): Int {
        return list_page_auth
    }

    override fun getListDividerBean(): ListDividerBean {
        if (dividerBean == null)
            return super.getListDividerBean()
        else
            return dividerBean!!
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