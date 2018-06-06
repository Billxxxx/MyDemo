package com.bill.billdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.bill.billdemo.R
import com.bill.billdemo.entity.CaidouApiCallBack
import com.bill.billdemo.entity.RecommendExpertListResp
import com.bill.billdemo.net.CaidouApi

@Route(path = "/bill/retrofit")
class RetrofitActivity : AppCompatActivity() {
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        textView = findViewById(R.id.txt2)
        findViewById<View>(R.id.refresh_btn).setOnClickListener { refresh() }
    }

    private fun refresh() {
//        CaidouApi.startRequest("v3_app_index_hj", object : CaidouApiCallBack<RecommendExpertListResp> {
//            override fun onFailure(t: Throwable) {
//                Log.d("TAG", "onFailure")
//            }
//
//            override fun onComplete() {
//                Log.d("TAG", "onComplete")
//            }
//
//            override fun onSuccess(data: RecommendExpertListResp) {
//                Log.d("TAG", "onSuccess")
//            }
//        })
    }
}
