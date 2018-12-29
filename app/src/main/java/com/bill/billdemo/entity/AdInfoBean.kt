package com.bill.billdemo.entity

import java.io.Serializable

class AdInfoBean {
    var imageInfo: ImageInfoBean? = null
    var action: ActionBean? = null

}

/**广告的点击动作 */
class ActionBean : Serializable {
    var type: Int = 0
    var url: String? = null
    /**
     * type : 3
     * ext : {"id":"14837840238450000","type":10}
     */

    var ext: ExtBean? = null

    companion object {
        //    0: 无操作,1: 内部浏览器打开连接, 2: 外部浏览器打开连接, 3: 跳转到具体页面
        val AdActionTypeNone = 0 //什么都不做
        val AdActionTypeAppWeb = 1//内跳网页
        val AdActionTypeOutsideWeb = 2   //外跳网页
        val ADActionPost = 3   //跳转到具体页面
        /**小程序 */
        val ADActionWX = 4   //外跳网页
    }
}


class ExtBean : Serializable {
    /**
     * id : 14837840238450000
     * type : 10
     */
    var type: Int = 0

    var id: String? = null
}