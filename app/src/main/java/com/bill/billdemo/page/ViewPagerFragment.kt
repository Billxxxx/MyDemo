package com.bill.billdemo.page

import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.arsenal.bill.util.RouterUtil
import com.arsenal.bill.util.createView
import com.bill.billdemo.R
import kotlinx.android.synthetic.main.view_pager_fragment.view.*

@Route(path = ARouterPageUtil.PAGE_VIEW_PAGER_FRAGMENT)
class ViewPagerFragment() : Fragment() {

    @Autowired(name = RouterUtil.VALUE_PAGE_AUTH)
    @JvmField
    var list_page_auth: Int = 0

    var mView: View? = null
    var adapter: PagerAdapter? = null

    override fun getView(): View? {
        if (mView == null) {
            mView = activity?.createView(R.layout.view_pager_fragment)
            adapter = object : PagerAdapter() {
                override fun isViewFromObject(view: View, any: Any): Boolean {
                    return view == any
                }

                override fun getCount(): Int {
                    return 0
                }

            }
            mView?.view_pager?.adapter = adapter
        }

        return mView
    }
}