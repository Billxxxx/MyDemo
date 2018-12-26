package com.bill.billdemo.page

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.activity.ArsenalBaseActivity
import com.arsenal.bill.entity.ListDividerBean
import com.arsenal.bill.recyclerview.BaseListAuth
import com.arsenal.bill.util.RouterUtil
import com.arsenal.bill.util.getColorById
import com.bill.billdemo.R
import com.bill.billdemo.entity.BaseListFragmentConfig
import com.bill.billdemo.entity.VHType
import com.bill.billdemo.net.RequestInfo


@Route(path = ARouterPageUtil.PAGE_MAIN_ACTIVITY)
class MainActivity : ArsenalBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val names = arrayOf(
                RouterUtil.PAGE_BASE_LIST_FRAGMENT,
                RouterUtil.PAGE_BASE_LIST_FRAGMENT,
                ARouterPageUtil.PAGE_MINE_FRAGMENT,
                ARouterPageUtil.PAGE_MINE_FRAGMENT)
        val fragment_data = arrayOf(
                BaseListFragmentConfig(
                        BaseListAuth.DISABLE_PULL_TO_REFRESH.authInt,
                        RequestInfo.V4_TABLOID,
                        arrayOf(VHType.TIME_FILTER, VHType.TABLOID_ITEM),
                        ListDividerBean(resources.getDimension(R.dimen.common_left_right), resources.getDimension(R.dimen.common_left_right), color = getColorById(R.color.line_color))
                ),
                BaseListFragmentConfig(
                        BaseListAuth.DISABLE_PULL_TO_REFRESH.authInt,
                        RequestInfo.V3_COMMUNITY_LIST,
                        arrayOf(VHType.COMMUNITY_TYPE))
        )
        val result = ARouter.getInstance().build(Uri.parse(ARouterPageUtil.PAGE_VIEW_PAGER_FRAGMENT))
                .withInt(RouterUtil.VALUE_INDEX, 0)
                .withObject(RouterUtil.VALUE_NAMES, names)
                .withObject(RouterUtil.VALUE_FRAGMENT_DATA, fragment_data)
                .withInt(RouterUtil.VALUE_OFFSCREEN_PAGE_LIMIT, 3)
                .withInt(RouterUtil.VALUE_INDICATOR_TYPE, ViewPagerIndicator.MAIN.ordinal)
                .navigation();
        if (result is Fragment)
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_base_layout, result)
                commit()
            }
    }
}