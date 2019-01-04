package com.bill.billdemo.net.resp

import com.arsenal.bill.net.IListResp
import com.arsenal.bill.net.IResp
import com.arsenal.bill.net.VHItemEntity
import com.arsenal.bill.recyclerview.IVHType
import com.bill.billdemo.entity.*

data class ReadResp(
        var topics: ArrayList<PostTimeBean>? = null,
        var jigouUsers: ArrayList<UserBean>? = null,
        var news: TabloidBean? = null,
        var banners: ArrayList<AdInfoBean>? = null,
        var communities: ArrayList<CommunityBean>? = null,
        var recommendFiles: ArrayList<DocPostBean>? = null
) : IResp(), IListResp {

    override fun getList(): ArrayList<VHItemEntity>? {
        val result = ArrayList<VHItemEntity>()
        if (banners != null || banners != null || communities != null)
            result.add(ReadHeader(news, banners, communities))
        topics?.forEach {
            if (it.topics != null)
                result.addAll(it.topics!!)
        }
        return result
    }
}

data class PostTimeBean(
        var time: String? = null,
        var num: Int = 0,
        var topics: List<PostBean>? = null) {

}

class TabloidBean {
    var newsTitles: ArrayList<String>? = null
}

/**帖子结构，包含问答文章等多种类型*/


class DocPostBean {}

class ReadHeader(
        var news: TabloidBean? = null,
        var banners: ArrayList<AdInfoBean>? = null,
        var communities: ArrayList<CommunityBean>? = null) : VHItemEntity() {
    override fun getVHType(): IVHType {
        return VHType.READ_HEAD
    }
}
