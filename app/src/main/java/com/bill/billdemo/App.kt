package com.bill.billdemo

import android.app.Application
import android.content.Context

import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.ArsenalApp
import com.arsenal.bill.net.BaseResp
import com.arsenal.bill.retrofit.NetHelper
import com.bill.billdemo.entity.CustomConverterFactory
import com.bill.billdemo.net.CDApiClient
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import retrofit2.Call

class App : Application() {
    lateinit var context: Context
    override fun onCreate() {
        super.onCreate()
        instance = this
        context = this

        ArsenalApp.context = this
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

        NetHelper.init(url, CustomConverterFactory.create()) {
            getCall(it)
        }

        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(1)         // (Optional) How many method line to show. Default 2
                //                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                //                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                //                .tag("")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }


    private fun getCall(commend: String): Call<BaseResp>? {

        val client: CDApiClient = NetHelper.getRetrofit().create(CDApiClient::class.java)

        val encrypt = DesEncrypt.getEncString(
                """{command:"${commend}",json:{
                                |"clienttype":"android",
                                |"imei":"9069635a-c252-48cb-8e3c-74f8be553ca11526866250425",
                                |"userId":"14938006781030000",
                                |"clientversion":"${BuildConfig.VERSION_CODE}",
                                |"limit":"20",
                                |"loadType":"0"}}""".trimMargin())

        return client.home_recommendForm(encrypt, App.getContext().getString(R.string.en_key))
    }

    companion object {
        var instance: App? = null
            private set

        fun getContext(): Context {
            return instance!!.context
        }
    }
}