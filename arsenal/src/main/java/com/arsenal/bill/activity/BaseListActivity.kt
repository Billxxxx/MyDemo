package com.arsenal.bill.activity

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.arsenal.bill.R
import com.arsenal.bill.recyclerview.IVHType
import com.arsenal.bill.recyclerview.MultipleItem
import com.arsenal.bill.recyclerview.MultipleItemQuickAdapter
import com.arsenal.bill.retrofit.BaseRequestInfo
import com.arsenal.bill.util.checkAuth
import com.arsenal.bill.util.dpToPx
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import java.io.Serializable

@Route(path = "/base/base_list")

open class BaseListActivity :
        ArsenalBaseActivity(),
        FlexibleDividerDecoration.PaintProvider,//分割线的画笔
        FlexibleDividerDecoration.VisibilityProvider,//分割线是否显示
        HorizontalDividerItemDecoration.MarginProvider //分割线左右间距
{

//    @Autowired(name = "vh_types")
//    @JvmField
    var vh_types: IVHType? = null
//    @Autowired(name = "resp")
//    @JvmField
    var resp: BaseRequestInfo? = null
//    @Autowired(name = "list_page_auth")
//    @JvmField
    var list_page_auth: Int = 0

    lateinit var mRecyclerView: RecyclerView
    var mSwipeRefreshLayout: SwipeRefreshLayout? = null

    var listPageAuthority: Int = 0
    lateinit var mAdapter: MultipleItemQuickAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
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
    }

    //不可以用kotlinX getLayout()可能被重写
    private fun initView() {
        mRecyclerView = findViewById(R.id.rv_list)
        mSwipeRefreshLayout = findViewById(R.id.swipe_layout)
        mSwipeRefreshLayout?.isEnabled = !listPageAuthority.checkAuth(BaseListAuth.DISABLE_PULL_TO_REFRESH.authInt)
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
     * 本页启用的ViewHolder
     */
    fun getVHTypes(): List<IVHType?> {
        return listOf(vh_types)
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