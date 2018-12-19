package com.bill.billdemo


import android.support.test.uiautomator.Configurator
import android.support.test.uiautomator.UiObject
import android.support.test.uiautomator.UiSelector
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.internal.matchers.TypeSafeMatcher

open class TestBase {

    fun delay() {
        val confg = Configurator.getInstance()
        confg.setWaitForSelectorTimeout(900000)
        val startuo = UiObject(UiSelector().className("android.view.View2"))
        startuo.longClickBottomRight()
    }

    fun first(expected: Matcher<View>): Matcher<View> {

        return object : TypeSafeMatcher<View>() {

            private var first = false

            override fun matchesSafely(item: View): Boolean {

                return if (expected.matches(item) && !first) {
                    first = true
                    true
                } else
                    false

            }

            override fun describeTo(description: Description) {
                description.appendText("Matcher.first( " + expected.toString() + " )")
            }
        }
    }

    fun sleep(sleep: Int) {
        try {
            Thread.sleep(sleep.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }
}
