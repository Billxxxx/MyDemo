package com.bill.billdemo.page

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.page.ArsenalBaseActivity
import com.bill.billdemo.R
import com.bill.billdemo.entity.BaseListFragmentConfig

@Route(path = ARouterPageUtil.PAGE_VIEW_PAGER_ACTIVITY)
class ViewPagerActivity : BaseActivity() {

    /**需要启动的fragment列表*/
    @Autowired(name = "names")
    @JvmField
    var names: Array<String>? = null

    @Autowired(name = "fragment_data")
    @JvmField
    var fragment_data: Array<BaseListFragmentConfig>? = null


    @Autowired(name = "index")
    @JvmField
    var index: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_fragment)

        if (names != null) {
            val result = ARouter.getInstance().build(Uri.parse(ARouterPageUtil.PAGE_VIEW_PAGER_FRAGMENT))
                    .withInt("index", index)
                    .withObject("names", names)
                    .withObject("fragment_data", fragment_data)
                    .navigation();
            if (result is Fragment)
                supportFragmentManager.beginTransaction().apply {
                    add(R.id.fragment_base_layout, result)
                    commit()
                }
        }
    }
}