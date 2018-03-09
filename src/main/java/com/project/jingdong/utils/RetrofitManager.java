package com.project.jingdong.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:封装retrofit
 * Detail:
 */

public class RetrofitManager {
    private Retrofit mRetrofit;
    private String baseUrl;
    private OkHttpClient client;
    private static RetrofitManager mRetrofitManager;

    private RetrofitManager(String baseUrl, OkHttpClient client) {
        this.baseUrl = baseUrl;
        this.client = client;
        initRetrofit();
    }

    public static synchronized RetrofitManager getInstance(String baseUrl, OkHttpClient client) {
        if (mRetrofitManager == null) {
            mRetrofitManager = new RetrofitManager(baseUrl, client);
        }
        return mRetrofitManager;
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public <T> T setCreate(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }
}
