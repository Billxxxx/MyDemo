package com.arsenal.bill.recyclerview

import com.arsenal.bill.net.VHItemEntity

class MultipleItem(var type: IVHType, var spanSize: Int = 0, var content: String? = null) : VHItemEntity() {
    override fun getVHType(): IVHType {
        return type
    }
}
