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
import com.arsenal.bill.util.MyLogger
import com.arsenal.bill.util.RouterUtil
import com.bill.billdemo.entity.BaseListFragmentConfig
import com.orhanobut.logger.Logger

/**通用的列表fragment，列表数据均由此列表实现，可以由Activity包装PAGE_BASE_LIST_ACTIVITY*/
@Route(path = RouterUtil.PAGE_BASE_LIST_FRAGMENT)
class BaseListFragment() : ArsenalListFragment() {

    @Autowired(name = "baseListConfig")
    @JvmField
    var baseListConfig: BaseListFragmentConfig? = null


    override fun getVHTypes(): List<IVHType?>? {
        return ArrayList<IVHType>().apply {
            baseListConfig?.vhTypes?.forEach {
                this.add(it)
            }
        }
    }

    override fun getRequestInfo(): BaseRequestInfo? {
        return baseListConfig?.apiInfo
    }

    override fun getListPageAuthority(): Int? {
        return baseListConfig?.pageAuth
    }

    override fun getListDividerBean(): ListDividerBean {
        if (baseListConfig?.listDividerBean == null)
            return super.getListDividerBean()
        else
            return baseListConfig?.listDividerBean!!
    }

    companion object {
        fun newInstance(bundle: Bundle): BaseListFragment {
            return BaseListFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        MyLogger.d("ViewPagerFragment inject")
        if (arguments != null) {
            ARouter.getInstance().inject(this)
        }
        Logger.d("ViewPagerFragment injected")

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}