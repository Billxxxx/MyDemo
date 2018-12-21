package com.bill.billdemo.page

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.recyclerview.BaseListAuth
import com.arsenal.bill.util.setApi
import com.arsenal.bill.util.setAuth
import com.arsenal.bill.util.setVHTypes
import com.bill.billdemo.BuildConfig
import com.bill.billdemo.R
import com.bill.billdemo.entity.ViewHolderType
import com.bill.billdemo.net.RequestInfo
import kotlinx.android.synthetic.main.ac_main.*

class AroutersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main)

        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }

        sample_list_btn.setOnClickListener {
            ARouter.getInstance().build("/bill/expert_main").navigation();
        }
        base_list_btn.setOnClickListener {
            try {
                ARouter.getInstance().build(PageUtil.BASE_LIST_ACTIVITY)
                        .setVHTypes(ViewHolderType.COMMUNITY_TYPE)
                        .setApi(RequestInfo.Community_List)
                        .setAuth(BaseListAuth.DISABLE_PULL_TO_REFRESH)
                        .navigation();
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        retrofit_tv.setOnClickListener {
            ARouter.getInstance().build("/bill/retrofit").navigation();
        }
    }
}