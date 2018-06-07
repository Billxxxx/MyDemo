package com.arsenal.bill.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

class RetrofitHelper(url: String, converter: Converter.Factory) {
    var retrofit: Retrofit

    companion object {
        var helper: RetrofitHelper? = null
            get() {
                return field
            }

        fun init(url: String, converter: Converter.Factory) {
            if (helper == null) {
                helper = RetrofitHelper(url, converter)
            }
        }

        fun getRetrofit(): Retrofit {
            return helper!!.retrofit
        }
    }

    init {
        val builder = Retrofit.Builder()
                .baseUrl(url).apply {
                    addConverterFactory(converter)
                }
        retrofit = builder.client(OkHttpClient.Builder().apply {
            //声明日志类
            addInterceptor(HttpLoggingInterceptor().apply {
                //设定日志级别
                level = HttpLoggingInterceptor.Level.BODY
            })

        }.build()).build()
    }
}
