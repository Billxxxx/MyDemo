package com.bill.billdemo.net

import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.net.BaseResp
import com.arsenal.bill.net.CaidouApiCallBack
import com.arsenal.bill.net.IResp
import com.arsenal.bill.retrofit.BaseRequestInfo
import com.arsenal.bill.retrofit.NetHelper
import com.arsenal.bill.util.getIntSharedPreferencesValue
import com.arsenal.bill.util.putIntToSharedPreferencesValue
import com.bill.billdemo.App
import com.bill.billdemo.BuildConfig
import com.bill.billdemo.R
import com.bill.billdemo.entity.CustomConverterFactory
import com.bill.billdemo.util.SystemParameter
import com.google.gson.Gson
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import retrofit2.Call
import retrofit2.Response

class RetrofitImpl {

    init {
        val url: String

        if (BuildConfig.DEBUG)
            if (surrentServiceIndex == 1) {
                // 这两行必须写在init之前，否则这些配置在init过程中将无效
                ARouter.openLog()     // 打印日志
                ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            }

//        正式环境下只读取第一个
        url = App.getContext().resources.getStringArray(R.array.service_url)[if (!BuildConfig.DEBUG) 0 else surrentServiceIndex]


        NetHelper.init(url, CustomConverterFactory.create(), ::getCall, ::onRequestResponse)
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(1)         // (Optional) How many method line to show. Default 2
                //                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                //                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                //                .tag("")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }

    private fun onRequestResponse(requestInfo: BaseRequestInfo, response: Response<BaseResp>?, callback: CaidouApiCallBack<IResp>) {
        if (response != null) {
            if (response.body()?.code == 0)
                callback.onSuccess(Gson().fromJson(response.body()?.json, requestInfo.getApiClazz()))
            else {
                callback.onFailure(Throwable("not success"))
            }
        }
        callback.onComplete()
    }

    private fun getCall(commend: String?): Call<BaseResp>? {

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
        /**环境配置*/
        var surrentServiceIndex: Int = App.getContext().getIntSharedPreferencesValue("RetrofitImpl", "service_index")
            set(value) {
                if (field != value) {
                    App.getContext().putIntToSharedPreferencesValue("RetrofitImpl", "service_index", value)
                    field = value
                }
            }
    }
}