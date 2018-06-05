package com.bill.billdemo.net;

import com.bill.billdemo.entity.BaseResp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CDApiClient {
    @FormUrlEncoded
    @POST("/app/appreq")
    Call<BaseResp> home_recommendForm(
            @Field("message") String message,
            @Field("isEncryption") String isEncryption
    );
}