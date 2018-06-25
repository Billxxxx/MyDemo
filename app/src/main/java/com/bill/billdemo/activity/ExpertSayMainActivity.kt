package com.bill.billdemo.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.TabHost
import com.alibaba.android.arouter.facade.annotation.Route
import com.arsenal.bill.activity.ArsenalBaseActivity
import com.bill.billdemo.R
import java.util.ArrayList

@Route(path = "/bill/expert_main")
class ExpertSayMainActivity : ArsenalBaseActivity() {
    var testIcon = R.mipmap.ic_launcher
    var testIconSelected = R.mipmap.ic_launcher_round
    internal var title = arrayOf("主页", "发现", "消息", "我")
    internal var nameIDs = intArrayOf(R.string.home_tab1, R.string.home_tab2, R.string.home_tab3, R.string.home_tab4)
    internal var tabIds = intArrayOf(R.id.tab1, R.id.tab2, R.id.tab3, R.id.tab4)
    internal var normalIDs = intArrayOf(testIconSelected, testIconSelected, testIconSelected, testIconSelected)
    internal var selectedIDs = intArrayOf(testIcon, testIcon, testIcon, testIcon)

    lateinit var mTabHost: TabHost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main_activity)

        mTabHost = this.findViewById(R.id.tab_host) as TabHost
        mTabHost.setup()

        val fragments: MutableList<Fragment>
        fragments = ArrayList()
        fragments.add(Fragment())
        fragments.add(Fragment())
        fragments.add(Fragment())
        fragments.add(Fragment())
        val bottomViews = ArrayList<MainBottomView>()
        // 生成底部自定义样式的按钮
        var i = 0
        while (i < title.size && i < tabIds.size) {
            bottomViews.add(MainBottomView(this, nameIDs[i], i, normalIDs[i], selectedIDs[i], R.color.black_selected_text, R.color.black_un_select_text, fragments[i]))
            mTabHost.addTab(mTabHost.newTabSpec(title[i]).setIndicator(bottomViews[i]).setContent(tabIds[i]))
            i++
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
    }
}
