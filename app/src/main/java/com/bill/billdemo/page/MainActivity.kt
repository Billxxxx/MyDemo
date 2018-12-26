package com.bill.billdemo.page

import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.page.ArsenalBaseActivity
import com.bill.billdemo.R
import com.bill.billdemo.controller.ViewPagerController
import com.bill.billdemo.controller.ViewPagerIndicatorType
import kotlinx.android.synthetic.main.activity_main.*


@Route(path = ARouterPageUtil.PAGE_MAIN_ACTIVITY)
class MainActivity : ArsenalBaseActivity() {
    override fun enableSwipeBack(): Boolean {
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val names = arrayOf(
                ARouterPageUtil.PAGE_MAIN_FRAGMENT,
                ARouterPageUtil.PAGE_MINE_FRAGMENT,
                ARouterPageUtil.PAGE_MINE_FRAGMENT,
                ARouterPageUtil.PAGE_MINE_FRAGMENT)


        val result = arrayListOf<Fragment>()
        names.forEachIndexed { index, s ->

            val fragment = ARouter.getInstance().build(s).apply {
            }.navigation()
            if (fragment is Fragment)
                result.add(fragment)
        }

        ViewPagerController(
                bottom_layout,
                view_pager,
                result,
                supportFragmentManager,
                3,
                ViewPagerIndicatorType.MAIN.ordinal)
    }
}