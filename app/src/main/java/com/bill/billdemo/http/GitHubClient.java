package com.bill.billdemo.http;

import com.bill.billdemo.activity.RequestBaseJson;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubClient {
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(
            @Path("user") String user
    );

    @FormUrlEncoded
    @POST("/storeUsers/accountChk")
    Call<List<GitHubRepo>> driver(
            @Field("clientversion")
                    int clientversion,
            @Field("account")
                    String account
    );

    @POST("/storeUsers/accountChk")
    Call<ResponseBody> base(@Body RequestBaseJson en);

}