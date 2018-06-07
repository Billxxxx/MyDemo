package com.arsenal.bill.net

import com.chad.library.adapter.base.entity.MultiItemEntity

interface IListResp {
    fun getList(): ArrayList<MultiItemEntity>
}
