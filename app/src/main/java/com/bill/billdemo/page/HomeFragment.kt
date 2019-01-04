package com.bill.billdemo.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.recyclerview.BaseListAuth
import com.arsenal.bill.util.RouterUtil
import com.arsenal.bill.util.createView
import com.bill.billdemo.R
import com.bill.billdemo.controller.ViewPagerController
import com.bill.billdemo.controller.ViewPagerIndicatorType
import com.bill.billdemo.entity.BaseListFragmentConfig
import com.bill.billdemo.entity.ListDividerMode
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
                    .putListConfig(RequestInfo.V4_TABLOID, BaseListAuth.DISABLE_AUTO_REFRESH.authInt, ListDividerMode.COMMON_PADDING_LEFT_RIGHT.listDivider, arrayOf(VHType.TIME_FILTER, VHType.NEWS))
                    .navigation() as Fragment)
            fragments.add(ARouter.getInstance().build(RouterUtil.PAGE_BASE_LIST_FRAGMENT)
                    .putListConfig(RequestInfo.HOME_READ_LIST, null, ListDividerMode.COMMON_PADDING_LEFT_RIGHT.listDivider,
                            arrayOf(VHType.POST_TEST_BASE,
                                    VHType.POST_ARTICLE,
                                    VHType.POST_ARTICLE_IMAGES,
                                    VHType.READ_HEAD))
                    .navigation() as Fragment)
            fragments.add(ARouter.getInstance().build(RouterUtil.PAGE_BASE_LIST_FRAGMENT)
                    .withObject(RouterUtil.VALUE_BASE_LIST_CONFIG, BaseListFragmentConfig(BaseListAuth.DISABLE_AUTO_REFRESH.authInt,
                            RequestInfo.V4_TABLOID,
                            arrayOf(VHType.TIME_FILTER, VHType.NEWS),
                            ListDividerMode.COMMON_PADDING_LEFT_RIGHT.listDivider))
                    .navigation() as Fragment)
            fragments.add(ARouter.getInstance().build(RouterUtil.PAGE_BASE_LIST_FRAGMENT)
                    .withObject(RouterUtil.VALUE_BASE_LIST_CONFIG, BaseListFragmentConfig(BaseListAuth.DISABLE_AUTO_REFRESH.authInt,
                            RequestInfo.V4_TABLOID,
                            arrayOf(VHType.TIME_FILTER, VHType.NEWS),
                            ListDividerMode.COMMON_PADDING_LEFT_RIGHT.listDivider))
                    .navigation() as Fragment)
            fragments.add(ARouter.getInstance().build(RouterUtil.PAGE_BASE_LIST_FRAGMENT)
                    .withObject(RouterUtil.VALUE_BASE_LIST_CONFIG, BaseListFragmentConfig(BaseListAuth.DISABLE_AUTO_REFRESH.authInt,
                            RequestInfo.V4_TABLOID,
                            arrayOf(VHType.TIME_FILTER, VHType.NEWS),
                            ListDividerMode.COMMON_PADDING_LEFT_RIGHT.listDivider))
                    .navigation() as Fragment)
            val viewPager = mView!!.findViewById<ViewPager>(R.id.view_pager)

            ViewPagerController(
                    mView!!,
                    viewPager,
                    fragments,
                    childFragmentManager,
                    3,
                    ViewPagerIndicatorType.HOME.ordinal)
//            viewPager.currentItem = 1
        }
        return mView
    }
}