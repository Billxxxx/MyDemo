package com.bill.billdemo.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.arsenal.bill.util.createView
import com.bill.billdemo.R

@Route(path = ARouterPageUtil.PAGE_MINE_FRAGMENT)
class MineFragment : Fragment() {
    var mView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView == null) {
            mView = activity?.createView(R.layout.fragment_mine)
        }
        return mView
    }
}