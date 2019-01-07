package com.arsenal.bill.retrofit

import com.arsenal.bill.net.BaseResp
import com.arsenal.bill.net.CaidouApiCallBack
import com.arsenal.bill.net.IResp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import java.util.*

class NetHelper(url: String,
                converter: Converter.Factory,
                var getCall: (String?, HashMap<String, Any>?) -> Call<BaseResp>?,
                var onRequestResponse: (requestInfo: BaseRequestInfo, response: Response<BaseResp>?, callback: CaidouApiCallBack<IResp>) -> Unit
) {
    var retrofit: Retrofit

    companion object {
        var helper: NetHelper? = null
            get() {
                return field
            }

        fun init(url: String,
                 converter: Converter.Factory,
                 getCall: (String?, HashMap<String, Any>?) -> Call<BaseResp>?,
                 onRequestResponse: (requestInfo: BaseRequestInfo, response: Response<BaseResp>?, callback: CaidouApiCallBack<IResp>) -> Unit
        ): NetHelper {
            if (helper == null) {
                helper = NetHelper(url, converter, getCall, onRequestResponse)
            }

            return helper!!
        }

        fun getRetrofit(): Retrofit {
            return helper!!.retrofit
        }

    }

    fun startRequest(requestInfo: BaseRequestInfo?, param: HashMap<String, Any>?, callback: CaidouApiCallBack<IResp>) {
        if (requestInfo == null) {
            callback.onComplete()
            return
        }
        val call = getCall(requestInfo.getApiCommand(), param)
        if (call == null) return
        call.enqueue(object : Callback<BaseResp> {
            override fun onResponse(call: Call<BaseResp>, response: Response<BaseResp>?) {
                onRequestResponse(requestInfo, response, callback)
            }

            override fun onFailure(call: Call<BaseResp>, t: Throwable) {
                t.printStackTrace()
                callback.onFailure(t)
                callback.onComplete()
            }
        })
    }

    init {
        val builder = Retrofit.Builder()
                .baseUrl(url).apply {
                    addConverterFactory(converter)
                }
        retrofit = builder.client(OkHttpClient.Builder().apply {
            //            addNetworkInterceptor(StethoInterceptor())
            //声明日志类
            addInterceptor(HttpLoggingInterceptor().apply {
                //设定日志级别
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()).build()
    }
}
