package com.arsenal.bill.util

import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String?, autoVisibility: Boolean = false) {
    if (!TextUtils.isEmpty(url)) {
        Glide.with(this.context).load(url).asBitmap().into(this)
        if (autoVisibility) {
            this.visible()
        }
    } else {
        if (autoVisibility) {
            this.gone()
        }
    }
}