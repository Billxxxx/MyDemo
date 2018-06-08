package com.bill.billdemo.entity

import com.arsenal.bill.net.IListResp
import com.arsenal.bill.net.IResp
import com.chad.library.adapter.base.entity.MultiItemEntity

class CommunityListResp : IResp(), IListResp {
    var datas: ArrayList<CommunityGroupBean>? = null

    override fun getList(): ArrayList<MultiItemEntity> {
        val list = ArrayList<MultiItemEntity>()
        datas?.forEach {
            list.addAll(it.children!!)
        }
        return list
    }
}