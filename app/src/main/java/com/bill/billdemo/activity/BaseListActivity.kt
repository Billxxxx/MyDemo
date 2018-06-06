package com.bill.billdemo.activity

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.arsenal.bill.BaseListActivity
import com.arsenal.bill.entity.IVHType
import com.bill.billdemo.entity.CaidouApiCallBack
import com.bill.billdemo.entity.RecommendExpertListResp
import com.bill.billdemo.entity.VHType
import com.bill.billdemo.net.CaidouApi
import com.bill.billdemo.net.IListResp
import com.bill.billdemo.net.IResp

@Route(path = "/bill/base_list")
class BaseListActivity : BaseListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CaidouApi(RecommendExpertListResp(), object : CaidouApiCallBack<IResp> {
            override fun onFailure(t: Throwable) {
                Log.d("TAG", "onFailure")
            }

            override fun onComplete() {
                Log.d("TAG", "onComplete")
            }

            override fun onSuccess(data: IResp?) {
                Log.d("TAG", "onSuccess")
                if (data is IListResp)
                    mAdapter.setNewData(data.getList())
            }
        })
    }

    override fun getVHTypes(): List<IVHType> {
        return arrayListOf(VHType.USER_TYPE)
    }
}