package com.bill.billdemo

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView

/**根据文字进行点击*/
fun String.click() {
    onView(withText(this)).perform(ViewActions.click())
}

/**根据ID进行点击*/
fun Int.click() {
    onView(withId(this)).perform(ViewActions.click())
}

/**根据文字判断是否显示在界面上*/
fun String.isDisplay() {
    onView(withText(this)).check(ViewAssertions.matches(isDisplayed()))
}

/**根据ID判断是否显示在界面上*/
fun Int.isDisplay() {
    onView(withId(this)).check(ViewAssertions.matches(isDisplayed()))
}

/**点击RecyclerView中的元素，默认0*/
fun clickListByPos(recyclerId: Int, position: Int = 0) {
    onView((withId(recyclerId))).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.click()))
}

/**点击返回按钮，默认返回一层*/
fun clickBack(count: Int = 1) {
    for (i in 0..count - 1)
        pressBack()
}

/**对指定ID输入文字*/
fun String.inputText(inputText: String) {
    onView(withHint(this)).perform(typeText(inputText));
}