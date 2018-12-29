package com.bill.billdemo.page

import com.arsenal.bill.page.ArsenalBaseActivity
import com.bill.billdemo.R
import com.bill.billdemo.net.RetrofitImpl

abstract class BaseActivity : ArsenalBaseActivity() {
    final override fun getServiceNames(): Array<String> {
        return resources.getStringArray(R.array.service_name)
    }

    final override fun saveServiceIndex(index: Int) {
        RetrofitImpl.surrentServiceIndex = index
    }
}