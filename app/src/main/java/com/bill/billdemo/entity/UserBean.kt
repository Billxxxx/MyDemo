package com.bill.billdemo.entity

import android.text.TextUtils

class UserBean {

    var name: String? = null
    var account: String? = null
    var bgLogo: String? = null
    var company: String? = null
    var authority: Int = 0
    var rName: String? = null
    var bIcon: String? = null

    val isGossip: Boolean
        get() = !TextUtils.isEmpty(rName)
    var realIcon: String? = null

    var icon: String? = null
    var iconUrl: String? = null
        get() = if (isGossip)
            bIcon
        else
            icon

    val showIcon: Boolean
        get() {
            return checkAuth(UserAuth.VISIBLE_USER.ordinal)
        }

    fun checkAuth(auth: Int): Boolean {
        return UserAuth.checkAuth(authority, auth)
    }
}

enum class UserAuth {
    /** 0 普通用户 */
    COMMON,
    /** 1 行家 */

    EXPERT,
    /** 2 机构 */

    ORGANIZATION,
    /** 3 自媒体 */

    SELF_MEDIA,
    /** 4 一般媒体 */

    MEDIA,
    /** 5 文章付费 */

    ARTICLE_PAY,
    /** 6 展示文章阅读数 */

    ARTICLE_READ_COUNT,
    /** 7 发文章 */

    SEND_ARTICLE,

    /** 8 发帖子 */
    SEND_POST,
    /** 9 回答问题 */
    SEND_QA,
    /** 10 发视频 */
    SEND_VIDEO,
    /** 11 信息流中是否显示用户用户信息 */
    VISIBLE_USER,

    HOLDER_12,

    HOLDER_13,
    /** 14 认证行家 */
    ENTICATION_EXPERT,

    /** 15 认证机构 */
    ENTICATION_ORGANIZATION,
    /** 16 认证作者 */
    AUTHOR,
    ;

    companion object {

        fun checkAuth(userAuth: Int, checkAuth: Int): Boolean {
            return userAuth and (1 shl checkAuth) != 0
        }
    }
}
