package com.bill.billdemo.viewholder

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arsenal.bill.recyclerview.BaseVH
import com.arsenal.bill.util.loadUrl
import com.arsenal.bill.views.BannerBaseAdapter
import com.arsenal.bill.views.SimpleGridRecyclerView
import com.bill.billdemo.R
import com.bill.billdemo.entity.AdInfoBean
import com.bill.billdemo.entity.CommunityBean
import com.bill.billdemo.net.resp.ReadHeader
import kotlinx.android.synthetic.main.item_read_community_grid.view.*
import kotlinx.android.synthetic.main.vh_banners.view.*
import java.io.Serializable

class BannersItemVH(i: LayoutInflater, p: ViewGroup)
    : BaseVH<ReadHeader>(R.layout.vh_banners, i, p) {
    var mBannerView: BannerView? = null

    init {
        itemView.setOnClickListener {

        }
        mBannerView = BannerView(itemView)
    }

    override fun setData(data: ReadHeader) {
        super.setData(data)
        mBannerView?.setData(data.banners, data.communities)

    }

    inner class BannerView(var rootView: View) {
        var simpleGridRecyclerView: SimpleGridRecyclerView<CommunityBean>? = null
        var banners: ArrayList<AdInfoBean>? = null
        var communities: ArrayList<CommunityBean>? = null

        var mAdapter: BannerAdapter

        private val datas = ArrayList<BannerBean>()

        init {
            mAdapter = BannerAdapter(rootView.context)
            rootView.bannerView.setAdapter(mAdapter)
            mAdapter.setOnPageTouchListener(object : BannerBaseAdapter.OnPageTouchListener<BannerBean> {
                override fun onPageClick(position: Int, bannerBean: BannerBean) {}

                override fun onPageDown() {
                    rootView.bannerView.stopAutoScroll()
                }

                override fun onPageUp() {
                    rootView.bannerView.startAutoScroll()
                }
            })

            rootView.category_layout.addView(
                    object : SimpleGridRecyclerView<CommunityBean>(
                            rootView.context,
                            5,
                            R.layout.item_read_community_grid) {
                        override fun setData(view: View, bean: CommunityBean, position: Int) {

                            if (position != getCount() - 1) {
                                view.community_icon.loadUrl(bean.logo)
                                view.community_name.text = bean.name
                            } else {
                                view.community_icon.setImageResource(R.drawable.icon_type_17)
                                view.community_name.text = "查看更多"
                            }
                            view.setOnClickListener {

                                //                                if (position != getCount() - 1) {
//                                    if (communities != null && mData!!.size > position) {
//                                        PanelForm.startDetail(mData!![position].id, false)
//                                    }
//                                } else {
//                                    CommonActivity.startActivity(CommonActivity.ALL_COMMUNITY)
//                                }
                            }
                        }
                    }.apply { simpleGridRecyclerView = this }.mRootView)
        }

        private fun setBanner() {

            if (banners == null || banners!!.size < 1) return
            datas.clear()

            for (adInfoBean in banners!!) {
                val bannerBean = BannerBean(adInfoBean.imageInfo?.imageUrl)
//                bannerBean.onClickListener = View.OnClickListener { v -> AdBaseViewHolder.click(v.context, adInfoBean) }
                datas.add(bannerBean)
            }

            // 数据更新之后需要设置
            mAdapter.setData(datas)
            if (datas.size > 1) {
                rootView.bannerView.startAutoScroll()
                rootView.bannerView.isAnimScroll(true)
            } else {
                rootView.bannerView.stopAutoScroll()
                rootView.bannerView.isAnimScroll(true)
            }
            Log.d("bannerTest", "setBanner end")
        }


        fun setData(banners: ArrayList<AdInfoBean>?, communities: ArrayList<CommunityBean>?) {
            this.banners = banners
            setBanner()
            if (communities != null) {
                this.communities = communities
                communities.add(CommunityBean())
                simpleGridRecyclerView?.setData(communities)
            }
            Log.d("bannerTest", "setData end")
        }
    }
}

class BannerAdapter(context: Context) : BannerBaseAdapter<BannerBean>(context) {

    override fun getLayoutResID(): Int {
        return R.layout.item_banner
    }

    override fun convert(convertView: View, data: BannerBean) {
        if (!TextUtils.isEmpty(data.imageUrl)) {
            val imageView = mConvertView.findViewById<View>(R.id.pageImage) as ImageView
//            ImageUtils.loadBitMap(data.imageUrl, imageView)
            imageView.loadUrl(data.imageUrl)
            if (data.onClickListener != null) {
                imageView.setOnClickListener(data.onClickListener)
            }
        }
    }
}


class BannerBean(var imageUrl: String?) : Serializable {
    var onClickListener: View.OnClickListener? = null
}

