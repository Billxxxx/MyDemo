package com.arsenal.bill.controller

import android.app.Activity
import android.graphics.Paint
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.arsenal.bill.R
import com.arsenal.bill.entity.ListDividerBean
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
import com.chad.library.adapter.base.BaseQuickAdapter
import com.orhanobut.logger.Logger
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import java.util.*

open class BaseListControl(var activity: Activity, var mIBaseListControl: IBaseListControl) {
    lateinit var mRecyclerView: RecyclerView
    open lateinit var mAdapter: MultipleItemQuickAdapter
    var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    var mRootView: View
    var isFirst = true

    init {
        mRootView = activity.layoutInflater.inflate(mIBaseListControl.getLayoutID(), null)
    }


    val mDividerDecoration = MyDividerDecoration()


    public fun init() {
        initView()
        initData()
    }

    private fun initView() {
        mRecyclerView = mRootView.findViewById(R.id.rv_list)
        mSwipeRefreshLayout = mRootView.findViewById(R.id.swipe_layout)
        val enable = if (mIBaseListControl.getListPageAuthority() == null) true else !mIBaseListControl.getListPageAuthority()!!.checkAuth(BaseListAuth.DISABLE_PULL_TO_REFRESH.authInt)
        if (enable)
            mSwipeRefreshLayout?.setOnRefreshListener {
                startRefresh()
            }
        mSwipeRefreshLayout?.isEnabled = enable
    }

    private fun initData() {
        mRecyclerView.setHasFixedSize(true)

        mAdapter = MultipleItemQuickAdapter(activity, null, mIBaseListControl.getVHTypes())
        mAdapter.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
            override fun onLoadMoreRequested() {
                Logger.d("开始加载更多")
                startRefresh(true)
            }
        }, mRecyclerView)
        val manager = GridLayoutManager(activity, 4)
        mRecyclerView.layoutManager = manager
        mAdapter.setSpanSizeLookup { _, position ->
            val data = mAdapter.getItem(position)
            if (data is MultipleItem) {
                data.spanSize
            } else {
                4
            }
        }
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(activity)
                .paintProvider(mDividerDecoration)
                .visibilityProvider(mDividerDecoration)
                .marginProvider(mDividerDecoration)
                .build())

        if (isAutoRefresh())
            startRefresh()
    }

    private fun startRefresh(loadMore: Boolean = false) {
        if (!loadMore)
            mSwipeRefreshLayout?.isRefreshing = true

        val param =
                if (!loadMore)
                    mIBaseListControl.getParam()
                else {
                    mIBaseListControl.getLoadMoreParam(mIBaseListControl.getParam())
                }

        NetHelper.helper?.startRequest(mIBaseListControl.getRequestInfo(), param, object : CaidouApiCallBack<IResp> {
            /**加载失败*/
            override fun onFailure(t: Throwable) {
                t.printStackTrace()
                if (loadMore)
                    mAdapter.loadMoreFail();
            }

            /**加载成功*/
            override fun onSuccess(data: IResp?) {
//                Log.d("TAG", "onSuccess")
                if (data is IListResp) {
                    val list = data.getList()
                    if (!loadMore)
                        mAdapter.setNewData(list)
                    else if (list != null) mAdapter.addData(list)
                }
            }

            /**加载完成*/
            override fun onComplete() {
//                Log.d("TAG", "onComplete")
                if (!loadMore)
                    mSwipeRefreshLayout?.isRefreshing = false
                else
                    mAdapter.loadMoreComplete();
            }
        })
    }


    /**
     * 是否自动初始化,默认：true
     */
    open fun isAutoRefresh(): Boolean {
        val result = mIBaseListControl.getListPageAuthority()?.checkAuth(BaseListAuth.DISABLE_AUTO_REFRESH.authInt)
        if (result == null) return true else return !result
    }

    fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser && !isAutoRefresh() && isFirst) {
            isFirst = false
            startRefresh()
        }
    }

    inner class MyDividerDecoration() : FlexibleDividerDecoration.PaintProvider,//分割线的画笔
            FlexibleDividerDecoration.VisibilityProvider,//分割线是否显示
            HorizontalDividerItemDecoration.MarginProvider //分割线左右间距
    {
        /**
         * 设置分割线画笔
         */
        override fun dividerPaint(position: Int, parent: RecyclerView?): Paint {
            val paint = Paint()
            paint.setColor(mIBaseListControl.getListDividerBean().color)
            paint.setStrokeWidth(mIBaseListControl.getListDividerBean().height.dpToPx())
            return paint
        }

        /**
         * 分割线左间距
         */
        override fun dividerLeftMargin(position: Int, parent: RecyclerView?): Int {
            return mIBaseListControl.getListDividerBean().left.toInt()
        }

        /**
         * 分割线右间距
         */
        override fun dividerRightMargin(position: Int, parent: RecyclerView?): Int {
            return mIBaseListControl.getListDividerBean().right.toInt()
        }

        /**
         * 是否需要隐藏分割线
         */
        override fun shouldHideDivider(position: Int, parent: RecyclerView?): Boolean {
            val item = mAdapter.getItem(position)
            if (item is ItemDividerDecoration) {
                return item.shouldHideDivider()
            }
            return false
        }
    }
}

interface IBaseListControl {

    /**
     * 本页启用的ViewHolder
     */
    fun getVHTypes(): List<IVHType?>?

    fun getListPageAuthority(): Int?
    /**
     * 默认布局
     */
    fun getLayoutID(): Int {
        return R.layout.ac_list
    }

    fun getListDividerBean(): ListDividerBean {
        return ListDividerBean()
    }

    fun getRequestInfo(): BaseRequestInfo? {
        return null
    }

    fun getParam(): HashMap<String, Any>? {
        return null
    }

    fun getLoadMoreParam(param: HashMap<String, Any>?): HashMap<String, Any>? {
        return param
    }
}

interface ItemDividerDecoration {
    fun shouldHideDivider(): Boolean
}
