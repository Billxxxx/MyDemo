package com.arsenal.bill

import android.databinding.ViewDataBinding

open class BaseDataBindingVH<B : ViewDataBinding, T>(var dataBinding: B) : BaseVH<T>(dataBinding.root) {

}