package com.bill.billdemo.entity

import com.arsenal.bill.entity.ListDividerBean
import com.arsenal.bill.util.getColorById
import com.bill.billdemo.App
import com.bill.billdemo.R

enum class ListDividerMode {
    COMMON_MATCH_PARENT {
        init {
            listDivider = ListDividerBean(color = App.getContext().getColorById(R.color.line_color))
        }
    },
    COMMON_PADDING_LEFT_RIGHT {
        init {
            val res = App.getContext().resources
            listDivider = ListDividerBean(
                    res.getDimension(R.dimen.common_left_right),
                    res.getDimension(R.dimen.common_left_right),
                    color = App.getContext().getColorById(R.color.line_color))

        }
    };

    lateinit var listDivider: ListDividerBean

}