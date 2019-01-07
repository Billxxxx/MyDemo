package com.arsenal.bill.page

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import com.arsenal.bill.BuildConfig
import com.arsenal.bill.util.BaseActivityManager
import com.arsenal.bill.util.showListDialog
import com.gyf.barlibrary.ImmersionBar
import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.OnItemClickListener
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.Utils
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper

abstract class ArsenalBaseActivity : AppCompatActivity(), SwipeBackActivityBase {
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

        BaseActivityManager.onAllActivityCreate(this)

        BaseActivityManager.activeActivity = this

        ImmersionBar.with(this).statusBarDarkFont(true).statusBarColor("#f2f2f2").init()

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

    override fun onResume() {
        super.onResume()
        BaseActivityManager.activeActivity = this
    }

    override fun onDestroy() {
        super.onDestroy()
        BaseActivityManager.onAllActivityDestroy(this)

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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (isFinishing) {
            return true
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (event?.getRepeatCount() == 0 && !onPanelKeyDown(keyCode, event)) {
                finish()
            }
            return true
        } else if (event != null && BuildConfig.DEBUG && event.getRepeatCount() > 0 && keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            // 如果当前是 debug 模式，则长按音量 - 键呼出 debug 工具面板
            showDebugControlDialog()
            return true
        } else {
            return super.onKeyDown(keyCode, event)
        }
    }

    /**
     * 替代原本重载的onKeyDown函数，各activity实例自己实现
     *
     * @return 不处理返回false 处理返回true
     */
    protected fun onPanelKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }

    fun showDebugControlDialog() {
        if (getServiceNames().size > 0)
            showListDialog(this, getServiceNames().toList(), object : OnItemClickListener {
                override fun onItemClick(dialog: DialogPlus?, item: Any?, view: View?, position: Int) {
                    saveServiceIndex(position)
                    dialog?.dismiss()
                }
            })
    }

    open fun getServiceNames(): Array<String> {
        return arrayOf()
    }

    open fun saveServiceIndex(index: Int) {

    }
}