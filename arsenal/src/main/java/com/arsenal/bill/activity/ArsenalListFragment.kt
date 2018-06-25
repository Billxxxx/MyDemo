package com.arsenal.bill.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsenal.bill.controller.BaseListControl
import com.arsenal.bill.controller.IBaseListControl

abstract class ArsenalListFragment : Fragment(), IBaseListControl {
    lateinit var baseListControl: BaseListControl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        baseListControl = BaseListControl(activity!!, this)
        baseListControl.init()
        return baseListControl.mRootView
    }
}