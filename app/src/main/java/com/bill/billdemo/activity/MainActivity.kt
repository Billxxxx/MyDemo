package com.bill.billdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.bill.billdemo.BuildConfig
import com.bill.billdemo.R
import kotlinx.android.synthetic.main.ac_main.*
import com.bill.billdemo.http.GitHubClient
import com.bill.billdemo.http.GitHubRepo
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main)

        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化

        list_btn.setOnClickListener {
            ARouter.getInstance().build("/bill/list").navigation();
        }
        retrofit_tv.setOnClickListener {
            ARouter.getInstance().build("/bill/retrofit").navigation();
        }
    }
}