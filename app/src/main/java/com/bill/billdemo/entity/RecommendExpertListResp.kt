package com.bill.billdemo.entity

import com.bill.billdemo.net.IListResp
import com.bill.billdemo.net.IResp
import com.chad.library.adapter.base.entity.MultiItemEntity

class RecommendExpertListResp : IResp(), IListResp {

    override fun getList(): ArrayList<MultiItemEntity> {
        val list = java.util.ArrayList<MultiItemEntity>()
        if (datas != null)
            list.addAll(datas!!)
        if (newProfessional != null)
            list.addAll(newProfessional!!)
        if (jigouUsers != null)
            list.addAll(jigouUsers!!)
        return list
    }

    var datas: ArrayList<UserBean>? = null
    var newProfessional: ArrayList<UserBean>? = null
    var jigouUsers: ArrayList<UserBean>? = null
}