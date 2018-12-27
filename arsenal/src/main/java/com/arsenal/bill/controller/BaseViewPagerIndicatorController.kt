package com.arsenal.bill.controller

import android.support.v4.view.ViewPager
import android.view.View

abstract class BaseViewPagerIndicatorController(view: View, viewPager: ViewPager, currentIndex: Int = 0, var selectCB: ((Int) -> Unit)) {
    var currentIndex = 0

    abstract fun setViewEnable(currentIndex: Int, b: Boolean)

    inner class TabOnClickListener(var index: Int) : View.OnClickListener {
        override fun onClick(p0: View?) {
            select(index)
            selectCB.invoke(index)
        }
    }

    init {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                select(position)
            }
        })
    }


    fun select(index: Int) {
        if (index != currentIndex) {
            setViewEnable(currentIndex, false)
            setViewEnable(index, true)
            currentIndex = index
        }
    }
}