package com.bill.billdemo.entity

import android.text.TextUtils

import java.io.Serializable

val JPG = 1
val GIF = 2
val VIDEO = 3

class ImageInfoBean : Serializable {

    var id: String? = null
    var imageUrl: String? = null
    var smallImageurl: String? = null
    var sImageurl: String? = null
    var mImageurl: String? = null

    /**
     * 1:jpg 2:gif 3:video
     */
    var type: Int = 0
    var path: String? = null
    var width: Double = 0.toDouble()
    var height: Double = 0.toDouble()

    val bigImageUtil: String?
        get() = if (TextUtils.isEmpty(imageUrl))
            smallImageurl
        else
            imageUrl


    fun getImageUrl(big: Boolean = false): String? {
        return if (big) {
            if (!TextUtils.isEmpty(imageUrl)) {
                imageUrl
            } else if (!TextUtils.isEmpty(mImageurl)) {
                mImageurl
            } else if (!TextUtils.isEmpty(smallImageurl)) {
                smallImageurl
            } else if (!TextUtils.isEmpty(sImageurl)) {
                sImageurl
            } else {
                null
            }
        } else {
            if (!TextUtils.isEmpty(smallImageurl)) {
                smallImageurl
            } else if (!TextUtils.isEmpty(sImageurl)) {
                sImageurl
            } else if (!TextUtils.isEmpty(imageUrl)) {
                imageUrl
            } else if (!TextUtils.isEmpty(mImageurl)) {
                mImageurl
            } else {
                null
            }
        }
    }

    constructor() {}

    constructor(imageUrl: String) {
        this.imageUrl = imageUrl
    }
}
