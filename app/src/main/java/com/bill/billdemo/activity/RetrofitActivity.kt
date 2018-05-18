package com.bill.billdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView

import com.alibaba.android.arouter.facade.annotation.Route
import com.bill.billdemo.R
import com.bill.billdemo.entity.CustomConverterFactory
import com.bill.billdemo.http.GitHubClient

import java.util.HashMap

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

@Route(path = "/bill/retrofit")
class RetrofitActivity : AppCompatActivity() {
    //    String API_BASE_URL = "https://api.github.com/";
    internal var API_BASE_URL = "http://test.idrive-technology.com/"
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        textView = findViewById(R.id.txt2)
        findViewById<View>(R.id.refresh_btn).setOnClickListener { refresh() }
    }

    private fun refresh() {
        val httpClient = OkHttpClient.Builder()
        //声明日志类
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        //设定日志级别
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(httpLoggingInterceptor)
        val builder = Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(CustomConverterFactory.create())
        val retrofit = builder.client(httpClient.build()).build()
        val client = retrofit.create(GitHubClient::class.java)

        val map = HashMap<String, String>()
        map["account"] = "15920100746"
        val call = client.base(RequestBaseJson("en01", map))

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("Callback", "onResponse")
                val stringBuffer = StringBuffer()

                if (response.body() != null) {
                    textView.text = stringBuffer
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
