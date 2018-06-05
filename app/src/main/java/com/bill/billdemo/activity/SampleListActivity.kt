package com.bill.billdemo.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.arsenal.bill.BaseListActivity
import com.arsenal.bill.entity.IVHType
import com.arsenal.bill.entity.MultipleItem
import com.bill.billdemo.entity.VHType
import com.chad.library.adapter.base.entity.MultiItemEntity
import java.util.*

@Route(path = "/bill/sample_list")
class SampleListActivity : BaseListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAdapter.setNewData(getMultipleItemData() as List<MultiItemEntity>?)
    }

    override fun getVHTypes(): List<IVHType> {
        return listOf(VHType.TEXT, VHType.IMAGE, VHType.IMAGE_TEXT)
    }

    fun getMultipleItemData(): MutableList<MultipleItem> {
        val list = ArrayList<MultipleItem>()
        for (i in 0..4) {
            list.add(MultipleItem(VHType.IMAGE, 1))
            list.add(MultipleItem(VHType.TEXT, 3, "CymChad"))
            list.add(MultipleItem(VHType.IMAGE_TEXT, 4))
            list.add(MultipleItem(VHType.IMAGE_TEXT, 2))
            list.add(MultipleItem(VHType.IMAGE_TEXT, 2))
        }

        return list
    }
}