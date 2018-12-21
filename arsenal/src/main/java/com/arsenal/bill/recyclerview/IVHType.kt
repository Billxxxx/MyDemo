package com.arsenal.bill.recyclerview

import android.os.Parcelable
import com.chad.library.adapter.base.entity.MultiItemEntity

interface IVHType : Parcelable {
    fun getItemType(): Int
    fun getVHClass(): Class<out BaseVH<out MultiItemEntity>>
    fun getLayoutId(): Int
}
