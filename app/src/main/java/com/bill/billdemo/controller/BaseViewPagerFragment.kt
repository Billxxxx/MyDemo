package com.bill.billdemo.controller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.util.createView
import com.bill.billdemo.R

abstract class BaseViewPagerFragment() : Fragment() {
    abstract fun getFragment(): ArrayList<Fragment>
    var mView: View? = null

    abstract fun getOffscreenPageLimit(): Int
    abstract fun getIndicatorType(): Int


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (arguments != null) {
            ARouter.getInstance().inject(this)
        }
        if (mView == null) {
            initView()
        }

        return mView
    }

    fun initView() {
        when (getIndicatorType()) {
            ViewPagerIndicatorType.HOME.ordinal -> {
                mView = activity?.createView(R.layout.view_pager_fragment)
            }
            ViewPagerIndicatorType.MAIN.ordinal -> {
                mView = activity?.createView(R.layout.activity_main)
            }
            else ->
                mView = activity?.createView(R.layout.view_pager_fragment)
        }
        ViewPagerController(
                mView!!,
                mView!!.findViewById(R.id.view_pager),
                getFragment(),
                activity!!.supportFragmentManager,
                getOffscreenPageLimit(),
                getIndicatorType())
    }
}