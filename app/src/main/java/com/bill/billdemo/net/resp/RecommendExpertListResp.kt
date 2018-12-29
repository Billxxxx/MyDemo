package com.bill.billdemo.net.resp

import com.arsenal.bill.net.IListResp
import com.arsenal.bill.net.IResp
import com.arsenal.bill.net.VHItemEntity
import com.bill.billdemo.entity.UserBean

class RecommendExpertListResp : IResp(), IListResp {

    override fun getList(): ArrayList<VHItemEntity> {
        val list = ArrayList<VHItemEntity>()
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