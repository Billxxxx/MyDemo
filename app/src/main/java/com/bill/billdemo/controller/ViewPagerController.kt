package com.bill.billdemo.controller

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.arsenal.bill.controller.BaseViewPagerIndicatorController
import com.arsenal.bill.util.createView
import com.arsenal.bill.util.dpToPx
import com.arsenal.bill.util.invisible
import com.arsenal.bill.util.visible
import com.bill.billdemo.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_top_tab.view.*

class ViewPagerController(
        rootView: View,
        viewPager: ViewPager,
        fragments: ArrayList<Fragment>,
        fragmentManager: FragmentManager,
        offscreenPageLimit: Int,
        indicatorType: Int) {
    var mAdapter: PagerAdapter? = null

    init {
        when (indicatorType) {
            ViewPagerIndicatorType.HOME.ordinal -> {
                HomeVPIndicatorController(rootView, viewPager, titles = arrayOf("新闻", "阅读", "图说", "热榜", "行家")) {
                    viewPager.currentItem = it
                }
            }
            ViewPagerIndicatorType.MAIN.ordinal -> {
                MainVPIndicatorController(rootView, viewPager) {
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

class HomeVPIndicatorController(view: View, viewPager: ViewPager, currentIndex: Int = 0, titles: Array<String>, selectCB: (Int) -> Unit)
    : BaseViewPagerIndicatorController(view, viewPager, currentIndex, selectCB) {


    /**问字标签*/
    var textViews = arrayListOf<TextView>()

    /**图片*/
    var imageViews = arrayListOf<ImageView>()

    init {


        textViews.clear()

        view.findViewById<ViewGroup>(R.id.view_pager_fragment_root_view).addView(
                LinearLayout(view.context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    titles.forEachIndexed { index, s ->

                        addView(
                                createView(R.layout.item_top_tab)?.apply {

                                    setOnClickListener(TabOnClickListener(index))

                                    textViews.add(this.text_view)
                                    imageViews.add(this.line_iv)

                                    this.text_view.text = s
                                    this.text_view.isEnabled = index == currentIndex

                                    if (index == currentIndex) {
                                        this.line_iv.visible()
                                    } else {
                                        this.line_iv.invisible()
                                    }
                                },
                                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT).apply {
                                    weight = 1f
                                }
                        )
                    }
                },
                0,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                    height = 49.dpToPx()
                }
        )
    }

    override fun setViewEnable(currentIndex: Int, b: Boolean) {
        if (currentIndex > -1) {
            if (currentIndex < textViews.size)
                textViews[currentIndex].isEnabled = b
            if (currentIndex < imageViews.size)
                if (b) {
                    imageViews[currentIndex].visible()
                } else {
                    imageViews[currentIndex].invisible()
                }
        }
    }
}

class MainVPIndicatorController(view: View, viewPager: ViewPager, currentIndex: Int = 0, selectCB: (Int) -> Unit)
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

    override fun setViewEnable(currentIndex: Int, b: Boolean) {
        if (currentIndex > -1) {
            if (currentIndex < textViews.size)
                textViews[currentIndex].isEnabled = b
            if (currentIndex < imageViews.size)
                imageViews[currentIndex].isEnabled = b
        }
    }
}
