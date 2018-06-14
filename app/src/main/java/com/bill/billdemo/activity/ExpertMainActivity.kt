package com.bill.billdemo.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.bill.billdemo.R
import com.bill.billdemo.databinding.ActivityWithViewPagerBinding
import com.orhanobut.logger.Logger
import java.util.*

@Route(path = "/bill/expert_main")
class ExpertMainActivity : AppCompatActivity() {
    private var bind: ActivityWithViewPagerBinding? = null
    private var adapter: VpAdapter? = null

    // collections
    private var fragments: ArrayList<Fragment> = ArrayList(3)// used for ViewPager adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        setContentView(R.layout.activity_with_view_pager);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_with_view_pager)

        initView()
        initData()
        initEvent()
    }

    /**
     * change BottomNavigationViewEx style
     */
    private fun initView() {
        //        bind.bnve.enableItemShiftingMode(true);
        bind!!.bnve.enableAnimation(false);
    }

    /**
     * create fragments
     */
    private fun initData() {
        fragments = ArrayList(3)

        // create music fragment and add it
        val musicFragment = BaseFragment()
        var bundle = Bundle()
        bundle.putString("title", "music")
        musicFragment.setArguments(bundle)

        // create backup fragment and add it
        val backupFragment = BaseFragment()
        bundle = Bundle()
        bundle.putString("title", "backup")
        backupFragment.setArguments(bundle)

        // create friends fragment and add it
        val friendsFragment = BaseFragment()
        bundle = Bundle()
        bundle.putString("title", "friends")
        friendsFragment.setArguments(bundle)

        // add to fragments for adapter
        fragments.add(musicFragment)
        fragments.add(backupFragment)
        fragments.add(friendsFragment)

        // set adapter
        adapter = VpAdapter(supportFragmentManager, fragments)
        bind!!.vp.setAdapter(adapter)

        // binding with ViewPager
        bind!!.bnve.setupWithViewPager(bind!!.vp)
    }

    /**
     * set listeners
     */
    private fun initEvent() {
        // set listener to do something then item selected
        bind!!.bnve.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            Logger.d(item.itemId.toString() + " item was selected-------------------")
            // you can return false to cancel select
            true
        })

    }

    /**
     * view pager adapter
     */
    private class VpAdapter(fm: FragmentManager, private val data: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(position: Int): Fragment {
            return data[position]
        }
    }


}
