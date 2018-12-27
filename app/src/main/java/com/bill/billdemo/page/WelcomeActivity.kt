package com.bill.billdemo.page

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.page.ArsenalBaseActivity

class WelcomeActivity : ArsenalBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().build(ARouterPageUtil.PAGE_MAIN_ACTIVITY)
                .navigation(this)

        finish()
    }

    override fun enableSwipeBack(): Boolean {
        return false
    }
}