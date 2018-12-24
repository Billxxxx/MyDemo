package com.bill.billdemo.page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.arsenal.bill.util.RouterUtil

@Route(path = RouterUtil.PAGE_BASE_LIST_FRAGMENT)
class TestFragment : Fragment() {
    @Autowired(name = "url")
    @JvmField
    var url: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return TextView(activity)
    }

}