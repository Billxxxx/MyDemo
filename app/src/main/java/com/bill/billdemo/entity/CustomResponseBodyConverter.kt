package com.bill.billdemo.entity

import com.arsenal.bill.net.BaseResp
import com.arsenal.bill.util.MyLogger
import com.bill.billdemo.net.DesEncrypt
import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type

internal class CustomResponseBodyConverter<T : BaseResp>(private val type: Type) : Converter<ResponseBody, BaseResp> {
    private val mResult: String? = null
    var jsonParser = JsonParser()

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): BaseResp? {
        val response = value.string()
        try {
            //解密
            try {
                //加密过的字符串
                val enjsonStr = jsonParser.parse(response).asJsonObject.get("json").asString
                val desString = DesEncrypt.getDesString(enjsonStr)
                MyLogger.json(desString)
                val jsonObject = jsonParser.parse(desString).asJsonObject
                val result = Gson().fromJson<BaseResp>(desString, type)
                return result
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        } finally {
            value.close()
        }
    }
}
