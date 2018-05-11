package com.bill.billdemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.bill.billdemo.R
import kotlinx.android.synthetic.main.ac_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main)
        list_btn.setOnClickListener {
//            ARouter.getInstance().
            ARouter.getInstance().build("/base/list").navigation();
        }
    }
}
