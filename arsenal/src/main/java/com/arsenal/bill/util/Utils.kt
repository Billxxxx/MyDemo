package com.arsenal.bill.util

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

fun Int.dpToPx(): Int {
    val scale = getScreenDensity()
    return (this * scale + 0.5f).toInt()
}

fun Float.dpToPx(): Float {
    val scale = getScreenDensity()
    return this * scale + 0.5f
}

//
fun Double.dpToPx(): Double {
    val scale = getScreenDensity()
    return this * scale + 0.5f
}
