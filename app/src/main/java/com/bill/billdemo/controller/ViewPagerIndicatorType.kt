package com.bill.billdemo.controller

import com.bill.billdemo.R

enum class ViewPagerIndicatorType {
    MAIN,
    HOME
    ;

    companion object {
        fun getLayoutByType(type: Int): Int {
            return when (type) {
                ViewPagerIndicatorType.HOME.ordinal -> {
                    R.layout.view_pager_fragment
                }
                ViewPagerIndicatorType.MAIN.ordinal -> {
                    R.layout.activity_main
                }
                else ->
                    R.layout.view_pager_fragment
            }
        }
    }
}
