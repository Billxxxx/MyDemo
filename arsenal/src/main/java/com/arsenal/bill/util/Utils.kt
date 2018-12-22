package com.arsenal.bill.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.arsenal.bill.ArsenalApp


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


fun View.createView(layoutId: Int): View? {
    return context.createView(layoutId)
}

fun Context.createView(layoutId: Int): View? {
    if (layoutId == 0) return null
    return LayoutInflater.from(this).inflate(layoutId, null)
}
