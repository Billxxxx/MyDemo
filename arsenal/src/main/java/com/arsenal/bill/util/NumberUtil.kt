package com.arsenal.bill.util


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
fun Int.checkAuth(authority:Int):Boolean{
    val that = this
        return that and authority == authority
}