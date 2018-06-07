package com.bill.billdemo.net

import com.arsenal.bill.retrofit.RetrofitHelper
import com.bill.billdemo.App
import com.bill.billdemo.BuildConfig
import com.bill.billdemo.DesEncrypt
import com.bill.billdemo.R
import com.bill.billdemo.entity.BaseResp
import com.bill.billdemo.entity.CaidouApiCallBack
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CaidouApi<T : RequestInfo>(var c: T?, callback: CaidouApiCallBack<IResp>) {
    init {
        if (c != null) {
            val call = getCall(c!!.command)

            // Execute the call asynchronously. Get a positive or negative callback.
            call?.enqueue(object : Callback<BaseResp> {
                override fun onResponse(call: Call<BaseResp>, response: Response<BaseResp>?) {
                    if (response != null) {
                        if (response.body()?.code == 0)
                            callback.onSuccess(Gson().fromJson(response.body()?.json, c!!.clazz))
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

    private fun getCall(commend: String): Call<BaseResp>? {

        val client: CDApiClient = RetrofitHelper.getRetrofit().create(CDApiClient::class.java)

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
}