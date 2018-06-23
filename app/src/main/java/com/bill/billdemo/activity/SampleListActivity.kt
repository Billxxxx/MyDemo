package com.bill.billdemo.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.arsenal.bill.activity.BaseListActivity
import com.arsenal.bill.recyclerview.IVHType
import com.arsenal.bill.recyclerview.MultipleItem
import com.bill.billdemo.entity.ViewHolderType
import com.chad.library.adapter.base.entity.MultiItemEntity
import java.util.*

@Route(path = "/bill/sample_list")
class SampleListActivity : BaseListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAdapter.setNewData(getMultipleItemData() as List<MultiItemEntity>?)
    }

//    override fun getVHTypes(): List<IVHType> {
//        return listOf(ViewHolderType.TEXT, ViewHolderType.IMAGE, ViewHolderType.IMAGE_TEXT)
//    }

    fun getMultipleItemData(): MutableList<MultipleItem> {
        val list = ArrayList<MultipleItem>()
        for (i in 0..4) {
            list.add(MultipleItem(ViewHolderType.IMAGE, 1))
            list.add(MultipleItem(ViewHolderType.TEXT, 3, "CymChad"))
            list.add(MultipleItem(ViewHolderType.IMAGE_TEXT, 4))
            list.add(MultipleItem(ViewHolderType.IMAGE_TEXT, 2))
            list.add(MultipleItem(ViewHolderType.IMAGE_TEXT, 2))
        }

        return list
    }
}