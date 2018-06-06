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
import com.chad.library.adapter.base.entity.MultiItemEntity
import java.util.*

@Route(path = "/bill/base_list")
class BaseListActivity : BaseListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CaidouApi(RecommendExpertListResp::class.java).startRequest("v3_app_index_hj", object : CaidouApiCallBack<RecommendExpertListResp> {
            override fun onFailure(t: Throwable) {
                Log.d("TAG", "onFailure")
            }

            override fun onComplete() {
                Log.d("TAG", "onComplete")
            }

            override fun onSuccess(data: RecommendExpertListResp?) {
                Log.d("TAG", "onSuccess")

                val list = ArrayList<MultiItemEntity>()
                if (data?.datas != null)
                    list.addAll(data.datas!!)
                if (data?.newProfessional != null)
                    list.addAll(data.newProfessional!!)
                if (data?.jigouUsers != null)
                    list.addAll(data.jigouUsers!!)
                mAdapter.setNewData(list as List<MultiItemEntity>?)
            }
        })
    }

    override fun getVHTypes(): List<IVHType> {
        return arrayListOf(VHType.USER_TYPE)
    }
}