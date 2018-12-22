package com.bill.billdemo.page

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.activity.ArsenalBaseActivity
import com.bill.billdemo.R

@Route(path = ARouterPageUtil.PAGE_VIEW_PAGER_ACTIVITY)
class ViewPagerActivity : ArsenalBaseActivity() {

    @Autowired(name = "urls")
    @JvmField
    var urls: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_fragment)

        if (urls != null) {
            val result = ARouter.getInstance().build(Uri.parse(urls!![0]))
                    .navigation();
            if (result is Fragment)
                supportFragmentManager.beginTransaction().apply {
                    add(R.id.fragment_base_layout, result)
                    commit()
                }
        }
    }
}

