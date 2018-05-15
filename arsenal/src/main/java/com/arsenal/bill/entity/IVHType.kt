package com.arsenal.bill.entity

import com.arsenal.bill.BaseVH

interface IVHType {
    fun getItemType(): Int
    fun getVHClass():Class<out BaseVH>
}
