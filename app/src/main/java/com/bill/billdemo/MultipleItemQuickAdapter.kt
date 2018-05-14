package com.bill.billdemo

import android.content.Context
import android.view.ViewGroup

import com.bill.billdemo.entity.VHType
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class MultipleItemQuickAdapter : BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    constructor(context: Context, data: MutableList<MultipleItem>, vararg vhTypes: VHType) : super(data) {
        vhTypes.forEach {
            addItemType(it.itemType, it.layoutId)
        }
    }

    override fun convert(helper: BaseViewHolder, item: MultipleItem) {
        when (helper.itemViewType) {
            MultipleItem.TEXT -> helper.setText(R.id.tv, item.content)
            MultipleItem.IMG_TEXT -> when (helper.layoutPosition % 2) {
                0 -> helper.setImageResource(R.id.iv, R.drawable.animation_img1)
                1 -> helper.setImageResource(R.id.iv, R.drawable.animation_img2)
            }
        }
    }



    override fun onCreateDefViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        return super.onCreateDefViewHolder(parent, viewType)
    }
}