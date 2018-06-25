package com.arsenal.bill.retrofit

import com.arsenal.bill.net.BaseResp
import com.arsenal.bill.net.CaidouApiCallBack
import com.arsenal.bill.net.IResp
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*

class NetHelper(url: String, converter: Converter.Factory, var getCall: (String) -> Call<BaseResp>?) {
    var retrofit: Retrofit

    companion object {
        var helper: NetHelper? = null
            get() {
                return field
            }

        fun init(url: String, converter: Converter.Factory, getCall: (String) -> Call<BaseResp>?): NetHelper {
            if (helper == null) {
                helper = NetHelper(url, converter, getCall)
            }

            return helper!!
        }

        fun getRetrofit(): Retrofit {
            return helper!!.retrofit
        }

    }

    fun startRequest(requestInfo: BaseRequestInfo?, callback: CaidouApiCallBack<IResp>) {
        if (requestInfo != null) {
            val call = getCall(requestInfo.getCommand())
            if (call != null) {
                call.enqueue(object : Callback<BaseResp> {
                    override fun onResponse(call: Call<BaseResp>, response: Response<BaseResp>?) {
                        if (response != null) {
                            if (response.body()?.code == 0)
                                callback.onSuccess(Gson().fromJson(response.body()?.json, requestInfo.getClazz()))
                            else {
                                callback.onFailure(Throwable("not success"))
                            }
                        }
                        callback.onComplete()
                    }

                    override fun onFailure(call: Call<BaseResp>, t: Throwable) {
                        t.printStackTrace()
                        callback.onFailure(t)
                        callback.onComplete()
                    }
                })
            }
        }
    }

    init {
        val builder = Retrofit.Builder()
                .baseUrl(url).apply {
                    addConverterFactory(converter)
                }
        retrofit = builder.client(OkHttpClient.Builder().apply {
            //声明日志类
            addInterceptor(HttpLoggingInterceptor().apply {
                //设定日志级别
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()).build()
    }
}
