package com.bill.billdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bill.billdemo.R;
import com.bill.billdemo.entity.CustomConverterFactory;
import com.bill.billdemo.http.GitHubClient;
import com.bill.billdemo.http.GitHubRepo;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Route(path = "/bill/retrofit")
public class RetrofitActivity extends AppCompatActivity {
    //    String API_BASE_URL = "https://api.github.com/";
    String API_BASE_URL = "https://testmobile.idrive-technology.com/";

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(CustomConverterFactory.create());

    Retrofit retrofit = builder.client(httpClient.build()).build();

    GitHubClient client = retrofit.create(GitHubClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        GitHubClient client = retrofit.create(GitHubClient.class);

// Fetch a list of the Github repositories.
//        Call<List<GitHubRepo>> call = client.reposForUser("fs-opensource");
        Call<List<GitHubRepo>> call = client.reposForUser(1);

// Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                Log.d("Callback", "onResponse");
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
