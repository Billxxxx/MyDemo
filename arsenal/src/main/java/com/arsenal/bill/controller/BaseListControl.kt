package com.arsenal.bill.controller

import android.app.Activity
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.R
import com.arsenal.bill.net.CaidouApiCallBack
import com.arsenal.bill.net.IListResp
import com.arsenal.bill.net.IResp
import com.arsenal.bill.recyclerview.BaseListAuth
import com.arsenal.bill.recyclerview.IVHType
import com.arsenal.bill.recyclerview.MultipleItem
import com.arsenal.bill.recyclerview.MultipleItemQuickAdapter
import com.arsenal.bill.retrofit.BaseRequestInfo
import com.arsenal.bill.retrofit.NetHelper
import com.arsenal.bill.util.checkAuth
import com.arsenal.bill.util.dpToPx
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration

open class BaseListControl(var activity: Activity, var mIBaseListControl: IBaseListControl) :
        FlexibleDividerDecoration.PaintProvider,//分割线的画笔
        FlexibleDividerDecoration.VisibilityProvider,//分割线是否显示
        HorizontalDividerItemDecoration.MarginProvider //分割线左右间距
{
    lateinit var mRecyclerView: RecyclerView
    open lateinit var mAdapter: MultipleItemQuickAdapter
    var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    var mRootView: View

    init {
        ARouter.getInstance().inject(activity);
        mRootView = activity.layoutInflater.inflate(mIBaseListControl.getLayoutID(), null)
    }

    public fun init() {
        initView()
        initData()
    }

    private fun initData() {
        mRecyclerView.setHasFixedSize(true)

        mAdapter = MultipleItemQuickAdapter(activity, null, mIBaseListControl.getVHTypes())
        val manager = GridLayoutManager(activity, 4)
        mRecyclerView.layoutManager = manager
        mAdapter.setSpanSizeLookup { _, position ->
            val data = mAdapter.data.get(position);
            if (data is MultipleItem) {
                data.spanSize
            } else {
                4
            }
        }
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(activity)
                .paintProvider(this)
                .visibilityProvider(this)
                .marginProvider(this)
                .build())

        NetHelper.helper?.startRequest(mIBaseListControl.getRequestInfo(), object : CaidouApiCallBack<IResp> {
            override fun onFailure(t: Throwable) {
                Log.d("TAG", "onFailure")
            }

            override fun onComplete() {
                Log.d("TAG", "onComplete")
            }

            override fun onSuccess(data: IResp?) {
                Log.d("TAG", "onSuccess")
                if (data is IListResp)
                    mAdapter.setNewData(data.getList())

            }
        })
    }

    private fun initView() {
        mRecyclerView = mRootView.findViewById(R.id.rv_list)
        mSwipeRefreshLayout = mRootView.findViewById(R.id.swipe_layout)
        mSwipeRefreshLayout?.isEnabled = !mIBaseListControl.getListPageAuthority().checkAuth(BaseListAuth.DISABLE_PULL_TO_REFRESH.authInt)
    }

    /**
     * 设置分割线画笔
     */
    override fun dividerPaint(position: Int, parent: RecyclerView?): Paint {
        val paint = Paint()
        paint.setColor(Color.LTGRAY)
        paint.setStrokeWidth(0.5f.dpToPx())
        return paint
    }

    /**
     * 分割线左间距
     */
    override fun dividerLeftMargin(position: Int, parent: RecyclerView?): Int {
        return 10.dpToPx()
    }

    /**
     * 分割线右间距
     */
    override fun dividerRightMargin(position: Int, parent: RecyclerView?): Int {
        return 10.dpToPx()
    }

    /**
     * 是否需要隐藏分割线
     */
    override fun shouldHideDivider(position: Int, parent: RecyclerView?): Boolean {
        return false
    }

    /**
     * 是否自动初始化,默认：true
     */
    open fun isAutoInit(): Boolean {
        return !mIBaseListControl.getListPageAuthority().checkAuth(BaseListAuth.DISABLE_AUTO_INIT.authInt)
    }
}

interface IBaseListControl {

    /**
     * 本页启用的ViewHolder
     */
    fun getVHTypes(): List<IVHType?>

    fun getListPageAuthority(): Int
    /**
     * 默认布局
     */
    fun getLayoutID(): Int {
        return R.layout.ac_list
    }

    open fun getRequestInfo(): BaseRequestInfo? {
        return null
    }
}
