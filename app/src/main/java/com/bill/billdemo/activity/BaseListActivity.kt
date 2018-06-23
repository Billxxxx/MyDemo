package com.bill.billdemo.activity

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.activity.ArsenalActivity
import com.arsenal.bill.net.IListResp
import com.arsenal.bill.net.IResp
import com.arsenal.bill.recyclerview.IVHType
import com.bill.billdemo.entity.CaidouApiCallBack
import com.bill.billdemo.entity.ViewHolderType
import com.bill.billdemo.net.CaidouApi
import com.bill.billdemo.net.RequestInfo


@Route(path = "/bill/base_list")
class BaseListActivity : ArsenalActivity() {

    @Autowired(name = "vh_types")
    @JvmField
    var vh_types: ViewHolderType? = null

    override fun getVHTypes(): List<IVHType?> {
        return listOf(vh_types)
    }

    @Autowired(name = "resp")
    @JvmField
    var resp: RequestInfo? = null

    @Autowired(name = "list_page_auth")
    @JvmField
    var list_page_auth: Int = 0

    override fun getListPageAuthority(): Int {
        return list_page_auth
    }

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

}