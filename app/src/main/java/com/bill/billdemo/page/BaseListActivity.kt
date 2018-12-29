package com.bill.billdemo.page

import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.util.RouterUtil
import com.arsenal.bill.util.RouterUtil.Companion.VALUE_BASE_LIST_CONFIG
import com.bill.billdemo.R
import com.bill.billdemo.entity.BaseListFragmentConfig

@Route(path = RouterUtil.PAGE_BASE_LIST_ACTIVITY)
class BaseListActivity : BaseActivity() {

    @Autowired(name = RouterUtil.VALUE_BASE_LIST_CONFIG)
    @JvmField
    var baseListConfig: BaseListFragmentConfig? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_fragment)
        val result = ARouter.getInstance().build(RouterUtil.PAGE_BASE_LIST_FRAGMENT)
                .withObject(VALUE_BASE_LIST_CONFIG, baseListConfig)
                .navigation();
        if (result is Fragment)
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_base_layout, result)
                commit()
            }
    }
}