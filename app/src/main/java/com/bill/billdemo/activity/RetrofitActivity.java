package com.bill.billdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bill.billdemo.R;
import com.bill.billdemo.entity.CustomConverterFactory;
import com.bill.billdemo.http.GitHubClient;
import com.bill.billdemo.http.GitHubRepo;

import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Route(path = "/bill/retrofit")
public class RetrofitActivity extends AppCompatActivity {
    //    String API_BASE_URL = "https://api.github.com/";
    String API_BASE_URL = "http://testmobile.idrive-technology.com/";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        textView = findViewById(R.id.txt2);
        findViewById(R.id.refresh_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });


    }

    private void refresh() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        //声明日志类
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//设定日志级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(httpLoggingInterceptor);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(CustomConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build()).build();
        GitHubClient client = retrofit.create(GitHubClient.class);


// Fetch a list of the Github repositories.
//        Call<List<GitHubRepo>> call = client.reposForUser("fs-opensource");
        Call<ResponseBody> call = client.base(new RequestBaseJson("en01", new HashMap<String, String>()));

// Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("Callback", "onResponse");
                StringBuffer stringBuffer = new StringBuffer();

                if (response.body() != null) {
//                    for (GitHubRepo repo : response.body()) {
//                        stringBuffer.append(repo.getName() + "\n");
//                    }
                    textView.setText(stringBuffer);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
