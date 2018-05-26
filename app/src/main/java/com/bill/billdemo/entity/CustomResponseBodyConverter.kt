package com.bill.billdemo.entity

import com.bill.billdemo.DesEncrypt
import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type

internal class CustomResponseBodyConverter(private val type: Type) : Converter<ResponseBody, BaseResp> {
    private val mResult: String? = null
    var jsonParser = JsonParser()

    init {

    }

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): BaseResp? {
        val response = value.string()
        try {
            //解密
            try {
                //加密过的字符串
                val enjsonStr = jsonParser.parse(response).asJsonObject.get("json").asString
                val desString = DesEncrypt.getDesString(enjsonStr)
                val deScriptedJsonObject = jsonParser.parse(desString).asJsonObject;
                val result = Gson().fromJson<BaseResp>(deScriptedJsonObject, type)
                result.jsonStr = desString
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

interface CaidouApiCallBack<T> {
    fun onSuccess(data: T);
    fun onFailure();
    fun onComplete();
}

open class BaseResp {
    var code: Int = -1
    var jsonStr: String? = null
}