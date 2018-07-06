package com.bill.billdemo.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.TabHost
import com.alibaba.android.arouter.facade.annotation.Route
import com.arsenal.bill.activity.ArsenalBaseActivity
import com.arsenal.bill.entity.ListDividerBean
import com.arsenal.bill.recyclerview.BaseListAuth
import com.bill.billdemo.R
import com.bill.billdemo.entity.ViewHolderType
import com.bill.billdemo.net.RequestInfo
import com.google.gson.Gson
import java.util.*

@Route(path = "/bill/expert_main")
class ExpertSayMainActivity : ArsenalBaseActivity(), TabHost.OnTabChangeListener {

    class TabHostBean(var titleId: Int, var normal: Int, var selected: Int, var id: Int) {}

    var currentTag: String? = null

    var tabHosts = arrayOf(
            TabHostBean(R.string.home_tab1, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, R.id.tab1),
            TabHostBean(R.string.home_tab2, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, R.id.tab2),
            TabHostBean(R.string.home_tab3, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, R.id.tab3),
            TabHostBean(R.string.home_tab4, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, R.id.tab4))

    lateinit var mTabHost: TabHost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main_activity)

        mTabHost = this.findViewById(R.id.tab_host) as TabHost
        mTabHost.setup()

        val fragments: MutableList<Fragment>
        fragments = ArrayList()
        fragments.add(Test2Fragment())
        fragments.add(BaseListFragment.newInstance(Bundle().apply {
            putString("resp", Gson().toJson(RequestInfo.Community_List))
            putString("vh_types", Gson().toJson(ViewHolderType.COMMUNITY_TYPE))
            putString("divider", Gson().toJson(ListDividerBean()))
        }))
        fragments.add(Test1Fragment())
        fragments.add(Test2Fragment())
        val bottomViews = ArrayList<MainBottomView>()
        // 生成底部自定义样式的按钮
        var i = 0
        while (i < tabHosts.size) {
            bottomViews.add(MainBottomView(this, tabHosts[i].titleId, i, tabHosts[i].normal, tabHosts[i].selected, R.color.black_selected_text, R.color.black_un_select_text, fragments[i]))
            mTabHost.addTab(mTabHost.newTabSpec(getString(tabHosts[i].titleId)).setIndicator(bottomViews[i]).setContent(tabHosts[i].id))
            i++
        }
        mTabHost.setOnTabChangedListener(this)
        onTabChanged("主页")
    }

    fun getDividerLeft() {

    }

    override fun onTabChanged(tag: String?) {
        var frag: Fragment? = null
        currentTag = tag
        var index = 0
        tabHosts.forEachIndexed { i, tabHostBean ->
            if (getString(tabHostBean.titleId) == tag) {
                index = i
            }
        }

        val view = mTabHost.tabWidget.getChildTabViewAt(index)
        if (view is MainBottomView) {
            view.isSelected = true
            frag = view.fragment
        }
        if (frag == null) {
            return
        }
        val manager = this.supportFragmentManager

        if (manager.findFragmentByTag(tag) == null) {
            val trans = manager.beginTransaction()
            trans.replace(tabHosts[index].id, frag, tag)
            trans.commit()
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
    }
}