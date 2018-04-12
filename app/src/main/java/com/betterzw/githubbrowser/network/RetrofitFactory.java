package com.betterzw.githubbrowser.network;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhengwu on 4/11/18.
 */
public class RetrofitFactory {

    public static Retrofit newInstance(@NonNull String baseUrl) {
        return new Retrofit.Builder()
                .client(OkHttpConnectionFactory.getClient().newBuilder().build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private RetrofitFactory() { }
}
