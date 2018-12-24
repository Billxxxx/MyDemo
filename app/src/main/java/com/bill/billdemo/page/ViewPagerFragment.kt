package com.bill.billdemo.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.util.RouterUtil.Companion.PAGE_BASE_LIST_FRAGMENT
import com.arsenal.bill.util.createView
import com.bill.billdemo.R
import com.bill.billdemo.entity.BaseListFragmentConfig
import kotlinx.android.synthetic.main.view_pager_fragment.view.*

@Route(path = ARouterPageUtil.PAGE_VIEW_PAGER_FRAGMENT)
class ViewPagerFragment() : Fragment() {

    @Autowired(name = "names")
    @JvmField
    var names: Array<String>? = null

    @Autowired(name = "fragment_data")
    @JvmField
    var fragment_data: Array<BaseListFragmentConfig>? = null

    @Autowired(name = "index")
    @JvmField
    var index: Int = 0

    var mView: View? = null
    var adapter: PagerAdapter? = null
    val fragments: ArrayList<Fragment> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (arguments != null) {
            ARouter.getInstance().inject(this)
        }
        if (mView == null) {
            mView = activity?.createView(R.layout.view_pager_fragment)
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
            mView?.view_pager?.adapter = adapter
        }

        return mView
    }
}