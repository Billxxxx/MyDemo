package com.bill.billdemo.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.util.RouterUtil
import com.arsenal.bill.util.RouterUtil.Companion.PAGE_BASE_LIST_FRAGMENT
import com.arsenal.bill.util.createView
import com.bill.billdemo.R
import com.bill.billdemo.entity.BaseListFragmentConfig
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_main.view.*

@Route(path = ARouterPageUtil.PAGE_VIEW_PAGER_FRAGMENT)
class ViewPagerFragment() : Fragment() {

    @Autowired(name = RouterUtil.VALUE_NAMES)
    @JvmField
    var names: Array<String>? = null

    @Autowired(name = RouterUtil.VALUE_FRAGMENT_DATA)
    @JvmField
    var fragment_data: Array<BaseListFragmentConfig>? = null

    @Autowired(name = RouterUtil.VALUE_INDEX)
    @JvmField
    var index: Int = 0

    @Autowired(name = RouterUtil.VALUE_OFFSCREEN_PAGE_LIMIT)
    @JvmField
    var offscreenPageLimit: Int = 1

    @Autowired(name = RouterUtil.VALUE_INDICATOR_TYPE)
    @JvmField
    var indicator_type: Int = -1


    var mView: View? = null
    var adapter: PagerAdapter? = null
    val fragments: ArrayList<Fragment> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (arguments != null) {
            ARouter.getInstance().inject(this)
        }
        Logger.d("ViewPagerFragment injected")
        if (mView == null) {
            initView()

            when (indicator_type) {
                ViewPagerIndicator.MAIN.ordinal ->
                    ViewPagerIndicatorController(mView!!, mView!!.view_pager) {
                        mView?.view_pager?.currentItem = it
                    }
            }
        }

        return mView
    }

    private fun initView() {
        mView = activity?.createView(R.layout.fragment_main)
        fragments.clear()
        names?.forEachIndexed { index, s ->

            val fragment = ARouter.getInstance().build(s).apply {
                if (s.equals(PAGE_BASE_LIST_FRAGMENT)) {
                    withObject("baseListConfig", fragment_data?.get(index))
                }
            }.navigation()
            if (fragment is Fragment)
                fragments.add(fragment)
        }

        adapter = object : FragmentPagerAdapter(activity?.supportFragmentManager) {
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
        mView?.view_pager?.apply {
            offscreenPageLimit = this@ViewPagerFragment.offscreenPageLimit
            adapter = this@ViewPagerFragment.adapter
        }
    }
}

enum class ViewPagerIndicator {
    MAIN
    ;
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

abstract class BaseViewPagerIndicatorController(view: View, viewPager: ViewPager, currentIndex: Int = 0, var selectCB: ((Int) -> Unit)) {
    var currentIndex = 0
    abstract fun select(index: Int)

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

    inner class TabOnClickListener(var index: Int) : View.OnClickListener {
        override fun onClick(p0: View?) {
            select(index)
            selectCB.invoke(index)
        }
    }
}