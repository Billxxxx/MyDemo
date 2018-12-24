package com.bill.billdemo.entity

import android.util.Log
import com.bill.billdemo.App
import com.bill.billdemo.R
import com.bill.billdemo.net.DesEncrypt
import com.bill.billdemo.page.RequestBaseJson
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Converter
import java.io.IOException
import java.nio.charset.Charset

/**
 * 加密
 *
 * @param <T>
</T> */
class CustomRequestBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<T, RequestBody> {

    @Throws(IOException::class)
    override fun convert(value: T): RequestBody {
        var valueStr = ""
        if (value is RequestBaseJson) {
            val requestBaseJson = value as RequestBaseJson
            val map = requestBaseJson.data
            try {
                val encrypt = DesEncrypt.getEncString(map.toString())
                requestBaseJson.message = encrypt
                Log.d("encrypt", encrypt)
                valueStr = "message=" + encrypt + "&isEncryption=" + App.getContext().getString(R.string.IS_ENCRYPTION_VALUE)
                //                valueStr = "message=%2FtFHDaYuvcwk%2FSCL5hIyx0CSHkM07O%2FY7A%2FMx0iPfKCsWpodFL3I0NcTvF9HXcvbVBj2qFaEnBxWuVSibt9KW33kqtfHyKdWNgMM144DYJaes07F2kO5UZERyvP7BcHy9GwaEmC3RZaW8fisUe8AVv7FOl%2FMDoaLoOhF6Qn5KsAvv1%2BvqAaY1tX8AvEZ%2BitkDAXKoS05klhv%2F%2BNKtQZY8INRFY1gu%2BOhZJB7jxwpEymd7xZZLvUQSFLOv5tozXjS5kwJ3tZsrFDY7RwanO2LsnzL0M3gMRdA2owHGvKGn58FyhUHl6EsryzKZ%2FTlpNjLKA4%2Bpb2gWkTe%2BCep1YpPWqaLx5MhMJHTa9%2F20YQ9n7lStCfmH74aG6s1G%2BI6fJx9&isEncryption=EN02&";
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return RequestBody.create(MEDIA_TYPE, valueStr)
    }

    companion object {
        private val MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8")
        private val UTF_8 = Charset.forName("UTF-8")
    }
}