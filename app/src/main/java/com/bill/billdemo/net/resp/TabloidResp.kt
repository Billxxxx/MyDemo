package com.bill.billdemo.net.resp

import com.arsenal.bill.controller.ItemDividerDecoration
import com.arsenal.bill.net.IListResp
import com.arsenal.bill.net.IResp
import com.arsenal.bill.net.VHItemEntity
import com.arsenal.bill.recyclerview.IVHType
import com.bill.billdemo.entity.VHType

class TabloidResp : IResp(), IListResp {

    override fun getList(): ArrayList<VHItemEntity>? {
        val result = ArrayList<VHItemEntity>()
        news?.forEachIndexed { index, newsGroupBean ->
            result.add(HomeTimeBean(newsGroupBean.time))
            newsGroupBean.news?.last()?.hideDivider = true
            newsGroupBean.news?.forEach {
                //                if (it.adInfo != null)
//                    result.add(it.adInfo!!)
//                else
                result.add(it)
            }
        }
        return result
    }

    //    var adInfo: AdInfoBean? = null
    var news: ArrayList<NewsGroupBean>? = null
    var cates: ArrayList<CategoryBeen>? = null
    var shareNewsImgUrl: String? = null
}


class NewsGroupBean {
    var news: ArrayList<NewsItemBean>? = null
    var time: String? = null
    var num: Int? = null
    var date: String? = null
    var category: ArrayList<CategoryBeen>? = null
}


class NewsItemBean(var author: String? = null,
                   var content: String? = null,
                   var date: String? = null,
                   var id: String? = null,
                   var pid: String? = null,
                   var time: String? = null,
                   var title: String? = null,
                   var type: Int? = null
//                   var adInfo: AdInfoBean? = null

) : VHItemEntity(), ItemDividerDecoration {
    var hideDivider = false
    override fun shouldHideDivider(): Boolean {
        return hideDivider
    }

    override fun getVHType(): IVHType {
        return VHType.NEWS
    }

    fun getUrl(): String {
        return "https://www.hangjianet.com/news/normaldetail?id=" + pid
    }
}

class CategoryBeen(var name: String = "", var type: Int = 0)

/**type :0阅读 1新闻*/
data class HomeTimeBean(var time: String?, var categories: ArrayList<CategoryBeen>? = null, var type: Int = 0)
    : VHItemEntity(), ItemDividerDecoration {
    override fun shouldHideDivider(): Boolean {
        return true
    }

    override fun getVHType(): IVHType {
        return VHType.TIME_FILTER
    }

}