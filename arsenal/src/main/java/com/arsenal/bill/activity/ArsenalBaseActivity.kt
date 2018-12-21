package com.arsenal.bill.activity

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.View
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.Utils
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper

abstract class ArsenalBaseActivity : FragmentActivity(), SwipeBackActivityBase{
    private var mHelper: SwipeBackActivityHelper? = null
    private var mSwipeBackLayout: SwipeBackLayout? = null

    protected open fun enableSwipeBack(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (enableSwipeBack()) {
            mHelper = SwipeBackActivityHelper(this)
            mHelper!!.onActivityCreate()
            swpieBackInit()
        }
    }

    private fun swpieBackInit() {
        mSwipeBackLayout = swipeBackLayout
        //设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout!!.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT)
        //mSwipeBackLayout.setEdgeSize(200);//滑动删除的效果只能从边界滑动才有效果，如果要扩大touch的范围，可以调用这个方法
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (enableSwipeBack()) {
            mHelper!!.onPostCreate()
        }
    }

    override fun <T : View?> findViewById(id: Int): T {
        if (enableSwipeBack()) {
            val v = super.findViewById<T>(id)
            return if (v == null && mHelper != null) mHelper!!.findViewById(id) as T else v
        } else
            return super.findViewById<T>(id)
    }

    override fun getSwipeBackLayout(): SwipeBackLayout {
        return mHelper!!.swipeBackLayout
    }

    override fun setSwipeBackEnable(enable: Boolean) {
        swipeBackLayout.setEnableGesture(enable)
    }

    override fun scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this)
        swipeBackLayout.scrollToFinishActivity()
    }

}
