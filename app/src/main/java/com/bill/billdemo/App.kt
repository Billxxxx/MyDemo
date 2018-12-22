package com.bill.billdemo

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.ArsenalApp
import com.bill.billdemo.net.RetrofitImpl
import com.bill.billdemo.util.UnCatchExceptionHandler

class App : Application() {
    lateinit var context: Context
    lateinit var application: Application
    override fun onCreate() {
        super.onCreate()
        instance = this
        context = this
        application = this

        ArsenalApp.context = this
        //初始化Stetho调试工具
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build())
        Thread.setDefaultUncaughtExceptionHandler(UnCatchExceptionHandler(this))

        RetrofitImpl()

        ARouter.init(application); // 尽可能早，推荐在Application中初始化
    }


    companion object {
        var instance: App? = null
            private set

        fun getContext(): Context {
            return instance!!.context
        }
    }
}