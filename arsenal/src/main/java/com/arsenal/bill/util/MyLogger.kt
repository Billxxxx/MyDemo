package com.arsenal.bill.util

import android.text.TextUtils
import com.arsenal.bill.BuildConfig
import com.orhanobut.logger.Logger

var ignoreBuildConfig = false

class MyLogger {

    companion object Factory {
        fun d(tag: String? = null, vararg text: Any) {
            if (ignoreBuildConfig && !BuildConfig.DEBUG) return
            if (TextUtils.isEmpty(tag))
                Logger.d(text)
            Logger.d(tag!!, text)
        }

        fun json(text: String) {
            if (ignoreBuildConfig && !BuildConfig.DEBUG) return
            Logger.json(text)
        }
    }
}