package com.arsenal.bill.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.text.TextUtils
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

fun Context.getColorById(colorId: Int): Int {
    return ContextCompat.getColor(this, colorId)
}

fun View.getColorById(colorId: Int): Int {
    return ContextCompat.getColor(this.context, colorId)
}


fun Context.getDrawableById(colorId: Int): Drawable {
    return ContextCompat.getDrawable(this, colorId)!!
}

fun View.getDrawableById(colorId: Int): Drawable {
    return ContextCompat.getDrawable(this.context, colorId)!!
}


fun Context.getDimensionById(colorId: Int): Float {
    return resources.getDimension(colorId)
}

fun View.getDimensionById(colorId: Int): Float {

    return resources.getDimension(colorId)
}


fun isNotEmpty(text: String?): Boolean {
    return !TextUtils.isEmpty(text)
}
fun isEmpty(text: String?): Boolean {
    return TextUtils.isEmpty(text)
}
