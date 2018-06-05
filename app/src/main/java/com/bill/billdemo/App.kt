package com.bill.billdemo

import android.app.Application
import android.content.Context

import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.RetrofitHelper
import com.bill.billdemo.entity.CustomConverterFactory
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

class App : Application() {
    lateinit var context: Context
    override fun onCreate() {
        super.onCreate()
        instance = this
        context = this
        //初始化Stetho调试工具
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())
        Thread.setDefaultUncaughtExceptionHandler(UnCatchExceptionHandler(this))

        val url: String
        if (BuildConfig.DEBUG) {
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            url = getString(R.string.debug_url)
        } else {
            url = getString(R.string.release_url)
        }
        RetrofitHelper.init(url, CustomConverterFactory.create())

        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(1)         // (Optional) How many method line to show. Default 2
                //                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                //                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                //                .tag("")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }

    companion object {
        var instance: App? = null
            private set

        fun getContext(): Context {
            return instance!!.context
        }
    }
}