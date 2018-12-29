package com.arsenal.bill.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.net.VHItemEntity
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter

class MultipleItemQuickAdapter(
        val context: Context,
        data: MutableList<VHItemEntity>?,
        var enableVHTypes: List<IVHType?>?) : BaseMultiItemQuickAdapter<VHItemEntity, BaseVH<VHItemEntity>>(data) {

    init {
        enableVHTypes?.forEach {
            if (it != null)
                addItemType(it.getItemType(), it.getLayoutId())
        }
    }

    override fun convert(helper: BaseVH<VHItemEntity>, item: VHItemEntity) {
        helper.setData(item)
    }

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseVH<VHItemEntity> {
        enableVHTypes?.forEach {
            if (it != null && it.getItemType() == viewType && it.getLayoutId() <= 0) {
                val constructor = Class.forName(it.getVHClass().name).getDeclaredConstructor(LayoutInflater::class.java, ViewGroup::class.java)
                //根据构造函数，传入值生成实例
                return constructor.newInstance(mLayoutInflater, parent) as BaseVH<VHItemEntity>
            }
        }
        try {
            return super.onCreateDefViewHolder(parent, viewType)
        } catch (e: Exception) {
            e.printStackTrace()
            return DefaultVH(mLayoutInflater)
        }
    }
}