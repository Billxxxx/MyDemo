package com.bill.billdemo.entity

import android.text.TextUtils
import com.arsenal.bill.net.VHItemEntity
import com.arsenal.bill.recyclerview.IVHType

data class PostBean(
        var type: Int = 0,
        var user: UserBean? = null,
        var answer: QABean? = null,
        var article: ArticlesBean? = null,
        /**点赞数量*/
        var likeNum: Int = 0,
        /**评论数量*/
        var commentNum: Int = 0

) : VHItemEntity() {
    override fun getVHType(): IVHType {
        return when (type) {
            PostType.ARTICLE.ordinal -> VHType.POST_ARTICLE
            else -> VHType.POST_TEST_BASE
        }
    }

    val title: String?
        get() = when (type) {
            PostType.ARTICLE.ordinal -> article?.title
            PostType.QA.ordinal -> answer?.wentiTitle
            else -> null
        }

    val content: String?
        get() = when (type) {
            PostType.ARTICLE.ordinal -> article?.shortContent
            PostType.QA.ordinal -> answer?.huidaContent
            else -> null
        }

    val userByType: UserBean?
        get() = when (type) {
            PostType.QA.ordinal -> answer?.huidaUser
            else -> user
        }

    /**信息流中的点赞与评论数*/
    val readCommentNum: String?
        get() {
            var result: String? = null
            if (likeNum > 0) {
                result = "赞 " + likeNum
            }
            if (commentNum > 0) {
                if (!TextUtils.isEmpty(result)) {
                    result += " · "
                }
                result += "评论 " + commentNum
            }
            return result
        }
}


enum class PostType {
    /**0 广告*/
    AD,//0
    /**1 文章*/
    ARTICLE,//1
    /**2 文章评论[分享出来的], 帖子格式[没有title], 正常帖子, 有title,content, 图文帖子*/
    TWITTER,//2//twitter
    /**3 问答帖子. 可能没有title*/
    QA,//3//answer
    /**4 视频帖子*/
    VIDEO,//4//video
    /**5 专题*/
    SPECIAL,//5//专题
    /**6 链接*/
    LINK,
    /**7 文件*/
    DOC,
    /**8 小报*/
    TABLOID,
    /** 9 热门帖子*/
    HOT_POST,
    /** 10 包含多个专题*/
    SPECIALS,
    /**11 图说*/
    IMAGE_SAY,
    HOLD,
    /** 13 周报*/
    WEELKLY,
}


data class QABean(
        var huidaUser: UserBean? = null,
        var date: String? = null,
        var id: String? = null,
        var wentiContent: String? = null,
        var wentiTitle: String? = null,
        var huidaContent: String? = null,
        var commentNum: String? = null,
        var liked: String? = null,
        var answerNum: Int = 0
)

data class ArticlesBean(
        var title: String? = null,
        var shortContent: String? = null
)