package com.bill.billdemo.activity

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.BaseListActivity
import com.arsenal.bill.entity.IVHType
import com.bill.billdemo.entity.CaidouApiCallBack
import com.bill.billdemo.entity.VHType
import com.bill.billdemo.net.CaidouApi
import com.bill.billdemo.net.IListResp
import com.bill.billdemo.net.IResp
import com.bill.billdemo.net.RequestInfo


@Route(path = "/bill/base_list")
class BaseListActivity : BaseListActivity() {
    @Autowired(name = "vh_types")
    @JvmField
    var vh_types: VHType? = null    // 支持解析自定义对象，URL中使用json传递
    @Autowired(name = "resp")
    @JvmField
    var resp: RequestInfo? = null    // 支持解析自定义对象，URL中使用json传递

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this);
        super.onCreate(savedInstanceState)
        CaidouApi(resp, object : CaidouApiCallBack<IResp> {
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

    override fun getVHTypes(): List<IVHType?> {
        return listOf(vh_types)
    }
}