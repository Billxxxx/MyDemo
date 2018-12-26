package com.bill.billdemo.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.util.RouterUtil
import com.arsenal.bill.util.RouterUtil.Companion.PAGE_BASE_LIST_FRAGMENT
import com.arsenal.bill.util.createView
import com.bill.billdemo.R
import com.bill.billdemo.controller.ViewPagerController
import com.bill.billdemo.controller.ViewPagerIndicatorType
import com.bill.billdemo.entity.BaseListFragmentConfig

@Route(path = ARouterPageUtil.PAGE_VIEW_PAGER_FRAGMENT)
class ViewPagerFragment() : Fragment() {
    var mView: View? = null

    @Autowired(name = RouterUtil.VALUE_NAMES)
    @JvmField
    var names: Array<String>? = null

    @Autowired(name = RouterUtil.VALUE_FRAGMENT_DATA)
    @JvmField
    var fragment_data: Array<BaseListFragmentConfig>? = null

    @Autowired(name = RouterUtil.VALUE_INDEX)
    @JvmField
    var index: Int = 0

    @Autowired(name = RouterUtil.VALUE_OFFSCREEN_PAGE_LIMIT)
    @JvmField
    var offscreenPageLimit: Int = 1

    @Autowired(name = RouterUtil.VALUE_INDICATOR_TYPE)
    @JvmField
    var indicator_type: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView == null) {
            mView = activity?.createView(ViewPagerIndicatorType.getLayoutByType(indicator_type))
            ViewPagerController(
                    mView!!,
                    mView!!.findViewById(R.id.view_pager),
                    getFragment(),
                    activity!!.supportFragmentManager,
                    offscreenPageLimit,
                    indicator_type)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun getFragment(): ArrayList<Fragment> {
        val result = arrayListOf<Fragment>()
        names?.forEachIndexed { index, s ->

            val fragment = ARouter.getInstance().build(s).apply {
                if (s.equals(PAGE_BASE_LIST_FRAGMENT)) {
                    withObject("baseListConfig", fragment_data?.get(index))
                }
            }.navigation()
            if (fragment is Fragment)
                result.add(fragment)
        }
        return result
    }
}