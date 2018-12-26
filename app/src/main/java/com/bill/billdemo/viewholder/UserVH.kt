package com.bill.billdemo.viewholder

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.recyclerview.BaseBindingVH
import com.bill.billdemo.R
import com.bill.billdemo.databinding.VhUserBinding
import com.bill.billdemo.entity.UserBean
import com.bill.billdemo.entity.VHType
import com.bill.billdemo.net.RequestInfo

class UserVH(var i: LayoutInflater, p: ViewGroup) : BaseBindingVH<VhUserBinding, UserBean>(DataBindingUtil.inflate(i, R.layout.vh_user, p, false)) {
    init {
        itemView.setOnClickListener {
            ARouter.getInstance().build("/bill/base_list")
                    .withObject("vh_types", VHType.COMMUNITY_TYPE)
                    .withObject("resp", RequestInfo.V3_COMMUNITY_LIST)
                    .navigation();
        }
    }

    override fun setData(data: UserBean) {
        super.setData(data)
        binding.user = data
    }
}