package com.arsenal.bill.net

import com.arsenal.bill.recyclerview.IVHType
import com.chad.library.adapter.base.entity.MultiItemEntity

interface IListResp {
    fun getList(): ArrayList<VHItemEntity>?
}

/**重写一下 方便自己控制*/
abstract class VHItemEntity : MultiItemEntity {
    abstract fun getVHType(): IVHType
    override fun getItemType(): Int {
        return getVHType().getItemType()
    }
}

/**通用的VH包装，比如包装一个数组*/
class CommonVHItem(var data: Any, var vhType: IVHType) : VHItemEntity() {
    override fun getVHType(): IVHType {
        return vhType
    }
}