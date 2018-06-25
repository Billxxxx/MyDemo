package com.arsenal.bill.activity

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.arsenal.bill.R
import com.arsenal.bill.net.CaidouApiCallBack
import com.arsenal.bill.net.IListResp
import com.arsenal.bill.net.IResp
import com.arsenal.bill.recyclerview.IVHType
import com.arsenal.bill.recyclerview.MultipleItem
import com.arsenal.bill.recyclerview.MultipleItemQuickAdapter
import com.arsenal.bill.retrofit.BaseRequestInfo
import com.arsenal.bill.retrofit.NetHelper
import com.arsenal.bill.util.checkAuth
import com.arsenal.bill.util.dpToPx
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import java.io.Serializable

abstract class ArsenalListActivity :
        ArsenalBaseActivity(),
        FlexibleDividerDecoration.PaintProvider,//分割线的画笔
        FlexibleDividerDecoration.VisibilityProvider,//分割线是否显示
        HorizontalDividerItemDecoration.MarginProvider //分割线左右间距
{

    lateinit var mRecyclerView: RecyclerView
    var mSwipeRefreshLayout: SwipeRefreshLayout? = null

    lateinit var mAdapter: MultipleItemQuickAdapter

    /**
     * 本页启用的ViewHolder
     */
    abstract fun getVHTypes(): List<IVHType?>

    abstract fun getListPageAuthority(): Int

    open fun getRequestInfo(): BaseRequestInfo? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        ARouter.getInstance().inject(this);
        if (isAutoInit())
            initListActivity()
    }

    /**
     * 初始化布局
     */
    open fun initListActivity() {
        initView()
        mRecyclerView.setHasFixedSize(true)

        mAdapter = MultipleItemQuickAdapter(this, null, getVHTypes())
        val manager = GridLayoutManager(this, 4)
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
        mRecyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(this)
                .paintProvider(this)
                .visibilityProvider(this)
                .marginProvider(this)
                .build())

        NetHelper.helper?.startRequest(getRequestInfo(), object : CaidouApiCallBack<IResp> {
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

    //不可以用kotlinX getLayout()可能被重写
    private fun initView() {
        mRecyclerView = findViewById(R.id.rv_list)
        mSwipeRefreshLayout = findViewById(R.id.swipe_layout)
        mSwipeRefreshLayout?.isEnabled = !getListPageAuthority().checkAuth(BaseListAuth.DISABLE_PULL_TO_REFRESH.authInt)
    }

    /**
     * 默认布局
     */
    open fun getLayoutID(): Int {
        return R.layout.ac_list
    }

    /**
     * 是否自动初始化,默认：true
     */
    open fun isAutoInit(): Boolean {
        return true
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
}


enum class BaseListAuth : Serializable {
    /**
     * 是否禁用下拉刷新，默认true
     */
    DISABLE_PULL_TO_REFRESH,
    /**
     * 是否禁用加载更多
     */
    DISABLE_MORE,
    /**
     * 是否禁用分割线
     */
    DISABLE_DIVIDER,
    /**
     * 是否启用刷新上一页面
     */
    ENABLE_BACK_REFRESH,
    /**
     * 是否禁用自动刷新
     */
    DISABLE_AUTO_REFRESH;

    var authInt: Int

    init {
        authInt = 1 shl ordinal
    }
}