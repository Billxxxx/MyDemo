package com.bill.billdemo.net

import android.util.Log
import com.arsenal.bill.RetrofitHelper
import com.bill.billdemo.DesEncrypt
import com.bill.billdemo.entity.BaseResp
import com.bill.billdemo.entity.CaidouApiCallBack
import com.bill.billdemo.entity.RecommendExpertListResp
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CaidouApi {
    companion object {

        fun startRequest(commend: String) {
            val client: GitHubClient = RetrofitHelper.getRetrofit().create(GitHubClient::class.java)

            var reques = """{command:"${commend}",json:{"clienttype":"android","imei":"9069635a-c252-48cb-8e3c-74f8be553ca11526866250425","userId":"14938006781030000","clientversion":"43","limit":"20","loadType":"0"}}"""
            val encrypt = DesEncrypt.getEncString(reques)

            val call = client.home_recommendForm(encrypt, "EN02")

            val callback = object : CaidouApiCallBack<RecommendExpertListResp> {
                override fun onFailure() {
                    Log.d("TAG", "onFailure")
                }

                override fun onComplete() {
                    Log.d("TAG", "onComplete")
                }

                override fun onSuccess(data: RecommendExpertListResp) {
                    Log.d("TAG", "onSuccess")
                }
            }

            // Execute the call asynchronously. Get a positive or negative callback.
            call.enqueue(object : Callback<BaseResp> {
                override fun onResponse(call: Call<BaseResp>, response: Response<BaseResp>) {
                    callback.onSuccess(Gson().fromJson(response.body()?.jsonStr, RecommendExpertListResp::class.java))
                    val stringBuffer = StringBuffer()
                }

                override fun onFailure(call: Call<BaseResp>, t: Throwable) {
                    t.printStackTrace()
                }
            })

        }
    }
}