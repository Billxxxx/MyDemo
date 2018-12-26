package com.bill.billdemo.page

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.activity.ArsenalBaseActivity

class WelcomeActivity : ArsenalBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ARouter.getInstance().build(ARouterPageUtil.PAGE_MAIN_ACTIVITY).navigation()
        finish()
    }
}