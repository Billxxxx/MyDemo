package com.arsenal.bill

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.entity.IVHType
import com.arsenal.bill.entity.MultipleItem
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity

class MultipleItemQuickAdapter(val context: Context, data: MutableList<MultiItemEntity>?, var enableVHTypes: List<IVHType>) : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {

    init {
        enableVHTypes.forEach {
            addItemType(it.getItemType(), it.getLayoutId())
        }
    }

    override fun convert(helper: BaseViewHolder, item: MultiItemEntity) {
        if (helper is BaseVH)
            helper.setData(item)
    }

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        enableVHTypes.forEach {
            if (it.getItemType() == viewType && it.getLayoutId() <= 0) {
                val constructor = Class.forName(it.getVHClass().name).getDeclaredConstructor(LayoutInflater::class.java, ViewGroup::class.java)
                //根据构造函数，传入值生成实例
                return constructor.newInstance(mLayoutInflater, parent) as BaseViewHolder
            }
        }
        return super.onCreateDefViewHolder(parent, viewType)
    }
}