package com.bill.billdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bill.billdemo.entity.VHType
import com.bill.billdemo.viewholder.BaseVH
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter

class MultipleItemQuickAdapter(val context: Context, data: MutableList<MultipleItem>, vararg vhTypes: VHType) : BaseMultiItemQuickAdapter<MultipleItem, BaseVH>(data) {
    lateinit var enableVHTypes: List<VHType>

    init {
        enableVHTypes = vhTypes.asList()
        vhTypes.forEach {
            addItemType(it.itemType, 0)
        }
    }

    override fun convert(helper: BaseVH, item: MultipleItem) {
        helper.setData(item)
    }

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        enableVHTypes.forEach {
            if (it.itemType == viewType) {
                val constructor = Class.forName(it.vhClass.name).getDeclaredConstructor(LayoutInflater::class.java, ViewGroup::class.java)
                //根据构造函数，传入值生成实例
                return constructor.newInstance(mLayoutInflater, parent) as BaseVH
            }
        }
        return super.onCreateDefViewHolder(parent, viewType)
    }
}