package com.bill.billdemo.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.entity.ListDividerBean
import com.arsenal.bill.recyclerview.BaseListAuth
import com.arsenal.bill.util.RouterUtil
import com.arsenal.bill.util.createView
import com.arsenal.bill.util.getColorById
import com.bill.billdemo.R
import com.bill.billdemo.controller.ViewPagerController
import com.bill.billdemo.controller.ViewPagerIndicatorType
import com.bill.billdemo.entity.BaseListFragmentConfig
import com.bill.billdemo.entity.VHType
import com.bill.billdemo.net.RequestInfo

@Route(path = ARouterPageUtil.PAGE_MAIN_FRAGMENT)
class HomeFragment() : Fragment() {
    var mView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView == null) {
            mView = activity?.createView(ViewPagerIndicatorType.getLayoutByType(ViewPagerIndicatorType.HOME.ordinal))


            val fragments = arrayListOf<Fragment>()

            fragments.add(ARouter.getInstance().build(RouterUtil.PAGE_BASE_LIST_FRAGMENT)
                    .withObject(RouterUtil.VALUE_BASE_LIST_CONFIG, BaseListFragmentConfig(BaseListAuth.DISABLE_PULL_TO_REFRESH.authInt,
                            RequestInfo.V4_TABLOID,
                            arrayOf(VHType.TIME_FILTER, VHType.TABLOID_ITEM),
                            ListDividerBean(resources.getDimension(R.dimen.common_left_right), resources.getDimension(R.dimen.common_left_right), color = activity!!.getColorById(R.color.line_color))))
                    .navigation() as Fragment)
            fragments.add(ARouter.getInstance().build(RouterUtil.PAGE_BASE_LIST_FRAGMENT)
                    .withObject(RouterUtil.VALUE_BASE_LIST_CONFIG, BaseListFragmentConfig(BaseListAuth.DISABLE_PULL_TO_REFRESH.authInt,
                            RequestInfo.V4_TABLOID,
                            arrayOf(VHType.TIME_FILTER, VHType.TABLOID_ITEM),
                            ListDividerBean(resources.getDimension(R.dimen.common_left_right), resources.getDimension(R.dimen.common_left_right), color = activity!!.getColorById(R.color.line_color))))
                    .navigation() as Fragment)
            fragments.add(ARouter.getInstance().build(RouterUtil.PAGE_BASE_LIST_FRAGMENT)
                    .withObject(RouterUtil.VALUE_BASE_LIST_CONFIG, BaseListFragmentConfig(BaseListAuth.DISABLE_PULL_TO_REFRESH.authInt,
                            RequestInfo.V4_TABLOID,
                            arrayOf(VHType.TIME_FILTER, VHType.TABLOID_ITEM),
                            ListDividerBean(resources.getDimension(R.dimen.common_left_right), resources.getDimension(R.dimen.common_left_right), color = activity!!.getColorById(R.color.line_color))))
                    .navigation() as Fragment)
            fragments.add(ARouter.getInstance().build(RouterUtil.PAGE_BASE_LIST_FRAGMENT)
                    .withObject(RouterUtil.VALUE_BASE_LIST_CONFIG, BaseListFragmentConfig(BaseListAuth.DISABLE_PULL_TO_REFRESH.authInt,
                            RequestInfo.V4_TABLOID,
                            arrayOf(VHType.TIME_FILTER, VHType.TABLOID_ITEM),
                            ListDividerBean(resources.getDimension(R.dimen.common_left_right), resources.getDimension(R.dimen.common_left_right), color = activity!!.getColorById(R.color.line_color))))
                    .navigation() as Fragment)
            fragments.add(ARouter.getInstance().build(RouterUtil.PAGE_BASE_LIST_FRAGMENT)
                    .withObject(RouterUtil.VALUE_BASE_LIST_CONFIG, BaseListFragmentConfig(BaseListAuth.DISABLE_PULL_TO_REFRESH.authInt,
                            RequestInfo.V4_TABLOID,
                            arrayOf(VHType.TIME_FILTER, VHType.TABLOID_ITEM),
                            ListDividerBean(resources.getDimension(R.dimen.common_left_right), resources.getDimension(R.dimen.common_left_right), color = activity!!.getColorById(R.color.line_color))))
                    .navigation() as Fragment)

            ViewPagerController(
                    mView!!,
                    mView!!.findViewById(R.id.view_pager),
                    fragments,
                    childFragmentManager,
                    3,
                    ViewPagerIndicatorType.HOME.ordinal)
        }
        return mView
    }
}