package com.bill.billdemo.net

import com.chad.library.adapter.base.entity.MultiItemEntity

interface IListResp {
    fun getList(): ArrayList<MultiItemEntity>
}
