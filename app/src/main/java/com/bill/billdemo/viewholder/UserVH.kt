package com.bill.billdemo.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsenal.bill.BaseDataBindingVH
import com.bill.billdemo.databinding.VhUserBinding
import com.bill.billdemo.entity.UserBean

class UserVH(var i: LayoutInflater, p: ViewGroup) : BaseDataBindingVH<VhUserBinding, UserBean>(VhUserBinding.inflate(i, p, false)) {

    override fun setData(data: UserBean) {
        super.setData(data)
        dataBinding.user = data
    }
}