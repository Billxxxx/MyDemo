package com.bill.billdemo.util

import android.content.SharedPreferences
import com.arsenal.bill.util.getStringList
import com.arsenal.bill.util.saveStringList
import com.bill.billdemo.App

/**
 * 本地变量，重装清零
 */
class LocalValue {
    var sp: SharedPreferences
        internal set

    val categorys = arrayOf(
            SelectCategory("显示", "3"),
            SelectCategory("LED", "2"),
            SelectCategory("照明", "1"))

    var categoryIds: ArrayList<String>
        set(value) {
            sp.saveStringList("selectedIds", value)
        }
        get() {
            var result = sp.getStringList("selectedIds")
            if (result == null) {
                result = arrayListOf()
                categorys.forEach {
                    result.add(it.id)
                }
            }
            return result
        }
    var communityIds: ArrayList<String>
        set(value) {
            sp.saveStringList("community_ids", value)
        }
        get() {
            var result = sp.getStringList("community_ids")
            if (result == null)
                result = arrayListOf()
            return result
        }

    init {
        sp = App.getContext().getSharedPreferences(TAG, 0)
    }

    companion object {
        internal var TAG = "localValue"
        internal var localValue: LocalValue = LocalValue()

        val instance: LocalValue
            get() {
                return localValue
            }
    }

    inner class SelectCategory(var name: String, var id: String) {
    }
}
