package com.arsenal.bill.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsenal.bill.controller.BaseListControl
import com.arsenal.bill.controller.IBaseListControl

abstract class ArsenalListFragment : Fragment(), IBaseListControl {
    var baseListControl: BaseListControl? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        baseListControl = BaseListControl(activity!!, this)
        baseListControl?.init()
        return baseListControl?.mRootView
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            baseListControl?.setUserVisibleHint(isVisibleToUser)
        }
    }
}