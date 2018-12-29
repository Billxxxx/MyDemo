package com.bill.billdemo.entity

import com.arsenal.bill.net.VHItemEntity
import com.arsenal.bill.recyclerview.IVHType

data class PostBean(
        var type: Int = 0,
        var user: UserBean? = null,
        var answer: QABean? = null,
        var article: ArticlesBean? = null

) : VHItemEntity() {
    override fun getVHType(): IVHType {
        return when (type) {

            else -> VHType.POST
        }
    }

    val title: String?
        get() {
            return when (type) {
                PostType.TYPE_ARTICLE.ordinal -> article?.title
                else -> null
            }
        }
    val content: String?
        get() {
            return when (type) {
                PostType.TYPE_ARTICLE.ordinal -> article?.shortContent
                else -> null
            }
        }

    val userByType: UserBean?
        get() {
            return when (type) {
                PostType.TYPE_QA.ordinal -> answer?.huidaUser
                else -> user
            }
        }
}

data class QABean(
        var huidaUser: UserBean? = null

)

data class ArticlesBean(
        var title: String? = null,
        var shortContent: String? = null
)

enum class PostType {


    //    * 0: 广告--?
    //    * 1: 文章
    //    * 2: 文章评论[分享出来的], 帖子格式[没有title], 正常帖子, 有title,content, 图文帖子
    //    * 3: 问答帖子. 可能没有title,
    //    * 4: 视频帖子.
    //    * 5: 专题
    /**
     * 暂时只有置顶
     */
    TYPE_AD,//0
    TYPE_ARTICLE,//1
    TYPE_TWITTER,//2//twitter
    TYPE_QA,//3//answer
    TYPE_VIDEO,//4//video
    TYPE_SPECIAL,//5//专题
    TYPE_LINK,//6
    /**7 文件*/
    TYPE_DOC,//
    /**8 小报*/
    TYPE_TABLOID,
    /** 9 热门帖子*/
    TYPE_HOT_POST,
    /** 10 包含多个专题*/
    TYPE_SPECIALS,
    /**11 图说*/
    TYPE_IMAGE_SAY,
    TYPE_HOLD,
    /** 13 周报*/
    TYPE_WEELKLY,
}
