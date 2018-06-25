package com.arsenal.bill.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.controller.BaseListControl
import com.arsenal.bill.controller.IBaseListControl

abstract class ArsenalListActivity :
        ArsenalBaseActivity(),
        IBaseListControl {

    lateinit var baseListControl: BaseListControl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        baseListControl = BaseListControl(this, this)
    }
}