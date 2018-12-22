package com.bill.billdemo.viewholder

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.recyclerview.BaseDataBindingVH
import com.bill.billdemo.R
import com.bill.billdemo.databinding.VhUserBinding
import com.bill.billdemo.entity.UserBean
import com.bill.billdemo.entity.ViewHolderType
import com.bill.billdemo.net.RequestInfo

class UserVH(var i: LayoutInflater, p: ViewGroup) : BaseDataBindingVH<VhUserBinding, UserBean>(DataBindingUtil.inflate(i, R.layout.vh_user, p, false)) {
    init {
        itemView.setOnClickListener {
            ARouter.getInstance().build("/bill/base_list")
                    .withObject("vh_types", ViewHolderType.COMMUNITY_TYPE)
                    .withObject("resp", RequestInfo.V3_COMMUNITY_LIST)
                    .navigation();
        }
    }

    override fun setData(data: UserBean) {
        super.setData(data)
        dataBinding.user = data
    }
}