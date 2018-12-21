package com.arsenal.bill.util

import com.alibaba.android.arouter.facade.Postcard
import com.arsenal.bill.ArsenalApp
import com.arsenal.bill.recyclerview.IVHType


private var screenDensity: Float = 0.toFloat()

/*初始化屏幕参数*/
fun initScreenParameter() {
    if (ArsenalApp.context == null)
        return
    val metrics = ArsenalApp.context!!.resources.displayMetrics
    screenDensity = metrics.density
}

//
///*获取屏幕像素密度*/
fun getScreenDensity(): Float {
    if (screenDensity == 0.0f) {
        initScreenParameter()
    }
    return screenDensity
}
