package com.arsenal.bill.entity

import android.graphics.Color

data class ListDividerBean(
        var left: Float = 0f,
        var right: Float = 0f,
        /**单位 DP */
        var height: Float = 0.25f,
        var color: Int = Color.LTGRAY
)