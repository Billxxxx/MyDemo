package com.bill.billdemo.http;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubClient {
        @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(
            @Path("user") String user
    );
//    @GET("/storeUsers/accountChk")
//    Call<List<GitHubRepo>> reposForUser(@Query("clientversion") int clientversion);
}