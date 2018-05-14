package com.bill.billdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.alibaba.android.arouter.facade.annotation.Route
import com.bill.billdemo.R
import kotlinx.android.synthetic.main.activity_test.*

@Route(path = "/bill/testactivity")
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        text_view.text = "set by kotlin"
    }
}
