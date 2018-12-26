package com.arsenal.bill.views


import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * 参考：http://www.jcodecraeer.com/a/anzhuokaifa/2015/0928/3525.html
 * Created by Administrator on 2017/5/3.
 */
class MyViewPager : ViewPager {
    private var downX: Float = 0.toFloat()
    private var downY: Float = 0.toFloat()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            downX = ev.x
            downY = ev.y
        } else if (ev.action == MotionEvent.ACTION_UP) {

            val upX = ev.x
            val upY = ev.y
            //如果 up的位置和down 的位置 距离 > 设置的距离,则事件继续传递,不执行下面的点击切换事件
            if (Math.abs(upX - downX) > DISTANCE || Math.abs(upY - downY) > DISTANCE) {
                return super.dispatchTouchEvent(ev)
            }

            val view = viewOfClickOnScreen(ev)
            if (view != null) {
                val index = view.tag as Int
                if (currentItem != index) {
                    currentItem = index
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * @param ev
     * @return
     */
    private fun viewOfClickOnScreen(ev: MotionEvent): View? {
        val childCount = childCount
        val currentIndex = currentItem
        val location = IntArray(2)
        for (i in 0 until childCount) {
            val v = getChildAt(i)
            val position = v.tag as Int
            v.getLocationOnScreen(location)
            var minX = location[0]
            val minY = location[1]

            var maxX = location[0] + v.width
            val maxY = location[1] + v.height

            if (position < currentIndex) {
                maxX -= (v.width * (1 - ScalePageTransformer.MIN_SCALE) * 0.5 + v.width * Math.abs(1 - ScalePageTransformer.MAX_SCALE) * 0.5).toInt()
                minX -= (v.width * (1 - ScalePageTransformer.MIN_SCALE) * 0.5 + v.width * Math.abs(1 - ScalePageTransformer.MAX_SCALE) * 0.5).toInt()
            } else if (position == currentIndex) {
                minX += (v.width * Math.abs(1 - ScalePageTransformer.MAX_SCALE)).toInt()
            } else if (position > currentIndex) {
                maxX -= (v.width * Math.abs(1 - ScalePageTransformer.MAX_SCALE) * 0.5).toInt()
                minX -= (v.width * Math.abs(1 - ScalePageTransformer.MAX_SCALE) * 0.5).toInt()
            }
            val x = ev.rawX
            val y = ev.rawY

            if (x > minX && x < maxX && y > minY && y < maxY) {
                return v
            }
        }
        return null
    }

    companion object {

        //默认距离
        private val DISTANCE = 10f
    }
}

class ScalePageTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        var position = position

        if (position < -1) {
            position = -1f
        } else if (position > 1) {
            position = 1f
        }

        val tempScale = if (position < 0) 1 + position else 1 - position

        val slope = (MAX_SCALE - MIN_SCALE) / 1
        //一个公式
        val scaleValue = MIN_SCALE + tempScale * slope
        page.scaleX = scaleValue
        page.scaleY = scaleValue
    }

    companion object {

        val MAX_SCALE = 1.0f
        val MIN_SCALE = 0.95f
    }
}