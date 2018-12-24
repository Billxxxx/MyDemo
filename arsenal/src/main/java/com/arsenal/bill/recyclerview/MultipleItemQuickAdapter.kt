package com.arsenal.bill.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity

class MultipleItemQuickAdapter(
        val context: Context,
        data: MutableList<MultiItemEntity>?,
        var enableVHTypes: List<IVHType?>?) : BaseMultiItemQuickAdapter<MultiItemEntity, BaseVH<MultiItemEntity>>(data) {

    init {
        enableVHTypes?.forEach {
            if (it != null)
                addItemType(it.getItemType(), it.getLayoutId())
        }
    }

    override fun convert(helper: BaseVH<MultiItemEntity>, item: MultiItemEntity) {
        helper.setData(item)
    }

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseVH<MultiItemEntity> {
        enableVHTypes?.forEach {
            if (it != null && it.getItemType() == viewType && it.getLayoutId() <= 0) {
                val constructor = Class.forName(it.getVHClass().name).getDeclaredConstructor(LayoutInflater::class.java, ViewGroup::class.java)
                //根据构造函数，传入值生成实例
                return constructor.newInstance(mLayoutInflater, parent) as BaseVH<MultiItemEntity>
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