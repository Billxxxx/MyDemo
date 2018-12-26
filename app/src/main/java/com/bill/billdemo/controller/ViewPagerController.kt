package com.bill.billdemo.controller

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.arsenal.bill.controller.BaseViewPagerIndicatorController
import kotlinx.android.synthetic.main.activity_main.view.*

class ViewPagerController(bottomView: View,
                          viewPager: ViewPager,
                          fragments: ArrayList<Fragment>,
                          fragmentManager: FragmentManager,
                          offscreenPageLimit: Int,
                          indicatorType: Int) {
    var mAdapter: PagerAdapter? = null

    init {
        when (indicatorType) {
            ViewPagerIndicatorType.HOME.ordinal -> {
            }
            ViewPagerIndicatorType.MAIN.ordinal -> {
                ViewPagerIndicatorController(bottomView, viewPager) {
                    viewPager.currentItem = it
                }
            }
            else -> {

            }
        }
        mAdapter = object : FragmentPagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment? {
                return if (position < fragments.size) {
                    fragments[position]
                } else
                    null
            }

            override fun getCount(): Int {
                return fragments.size
            }
        }
        viewPager.apply {
            this.offscreenPageLimit = offscreenPageLimit
            adapter = mAdapter
        }
    }
}

class ViewPagerIndicatorController(view: View, viewPager: ViewPager, currentIndex: Int = 0, selectCB: (Int) -> Unit)
    : BaseViewPagerIndicatorController(view, viewPager, currentIndex, selectCB) {

    /**问字标签*/
    var textViews = arrayListOf<TextView>()

    /**图片*/
    var imageViews = arrayListOf<ImageView>()

    init {
        textViews.clear()
        textViews.add(view.tab_tv_1)
        textViews.add(view.tab_tv_2)
        textViews.add(view.tab_tv_3)
        textViews.add(view.tab_tv_4)

        imageViews.clear()
        imageViews.add(view.tab_iv_1)
        imageViews.add(view.tab_iv_2)
        imageViews.add(view.tab_iv_3)
        imageViews.add(view.tab_iv_4)

        textViews.forEachIndexed { index, textView ->
            textView.isEnabled = index == currentIndex
        }
        imageViews.forEachIndexed { index, textView ->
            textView.isEnabled = index == currentIndex
        }

        view.tab_layout_1.setOnClickListener(TabOnClickListener(0))
        view.tab_layout_2.setOnClickListener(TabOnClickListener(1))
        view.tab_layout_3.setOnClickListener(TabOnClickListener(2))
        view.tab_layout_4.setOnClickListener(TabOnClickListener(3))
    }

    override fun select(index: Int) {
        if (index != currentIndex) {
            setViewEnable(currentIndex, false)
            setViewEnable(index, true)
            currentIndex = index
        }
    }

    private fun setViewEnable(currentIndex: Int, b: Boolean) {
        if (currentIndex > -1) {
            if (currentIndex < textViews.size)
                textViews[currentIndex].isEnabled = b
            if (currentIndex < imageViews.size)
                imageViews[currentIndex].isEnabled = b
        }
    }
}
