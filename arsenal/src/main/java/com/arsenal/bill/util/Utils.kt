package com.arsenal.bill.util

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.text.style.ReplacementSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.arsenal.bill.ArsenalApp
import com.arsenal.bill.views.IconTextSpan
import java.io.IOException


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

fun Context.putIntToSharedPreferencesValue(sharedPreferencesKey: String, valueKey: String, value: Int?) {
    if (value == null) return
    getSharedPreferences(sharedPreferencesKey, 0).apply {
        edit().apply {
            putInt(valueKey, value)
            apply()
        }
    }
}

fun Context.getIntSharedPreferencesValue(sharedPreferencesKey: String, valueKey: String, defaultValue: Int = 0): Int {
    getSharedPreferences(sharedPreferencesKey, 0).apply {
        return getInt(valueKey, defaultValue)
    }
}


fun TextView.setLeftTagText(tagStr: String, tagBackgroundColor: Int, text: String) {
    val spans = kotlin.collections.arrayListOf<ReplacementSpan>()
    val stringBuilder = StringBuilder()
    //第一个Span
    stringBuilder.append(" ")
    val hotSpan = IconTextSpan(context, tagBackgroundColor, tagStr)
    hotSpan.setRightMarginDpValue(5)
    spans.add(hotSpan)

    stringBuilder.append(text)
    val spannableString = android.text.SpannableString(stringBuilder.toString())
    //循环遍历设置Span
    for (i in spans.indices) {
        spannableString.setSpan(spans.get(i), i, i + 1, android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    setText(spannableString)
}

fun SharedPreferences.saveStringList(key: String, list: List<String>?) {
    try {
        val edit = edit()
        var strings = ""
        if (list != null)
            for (serializable in list) {
                strings += serializable + ",;"
            }
        if (!TextUtils.isEmpty(strings) && !TextUtils.isEmpty(key)) {
            edit.putString(key, strings)
            edit.commit()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun SharedPreferences.getStringList(key: String): ArrayList<String>? {
    val string = getString(key, null)
    if (!TextUtils.isEmpty(string))
        try {
            val objects = ArrayList<String>()

            val strings = string!!.split(",;".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (str in strings) {
                if (!TextUtils.isEmpty(str)) {
                    objects.add(str)
                }
            }
            return objects
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

    return null
}