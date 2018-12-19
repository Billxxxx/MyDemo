package com.bill.billdemo

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import com.bill.billdemo.page.AroutersActivity
import org.junit.Rule
import org.junit.Test

class WXSpider : TestBase() {

    @Rule
    @JvmField
    val brandListActivity: ActivityTestRule<AroutersActivity> = ActivityTestRule(AroutersActivity::class.java, true, true)


    @Test
    @Throws(Exception::class)
    fun normal_cancel() {

        val home = Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        brandListActivity.activity.startActivity(home)


        "通讯".click()
        sleep(100000)

    }
}
