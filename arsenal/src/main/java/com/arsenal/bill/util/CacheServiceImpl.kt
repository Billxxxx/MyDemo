package com.arsenal.bill.util

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.google.gson.Gson
import java.lang.reflect.Type
/**ARoute 传递参数需要使用的类*/
@Route(path = "/service/json")
class CacheServiceImpl : SerializationService {
    lateinit var gson: Gson

    override fun init(context: Context) {
        gson = Gson()
    }

    override fun object2Json(instance: Any): String {
        return gson.toJson(instance)
    }

    override fun <T : Any> json2Object(input: String, clazz: Class<T>): T = parseObject(input, clazz)

    override fun <T : Any> parseObject(input: String, clazz: Type): T {
        return gson.fromJson<T>(input, clazz)
    }
}
