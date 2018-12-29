package com.bill.billdemo.net.resp

import com.arsenal.bill.net.IListResp
import com.arsenal.bill.net.IResp
import com.arsenal.bill.net.VHItemEntity
import com.bill.billdemo.entity.CommunityGroupBean

class CommunityListResp : IResp(), IListResp {
    var datas: ArrayList<CommunityGroupBean>? = null

    override fun getList(): ArrayList<VHItemEntity> {
        val list = ArrayList<VHItemEntity>()
        datas?.forEach {
            list.addAll(it.children!!)
        }
        return list
    }
}