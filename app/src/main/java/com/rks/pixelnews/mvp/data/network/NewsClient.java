package com.rks.pixelnews.mvp.data.network;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Coolalien on 12/5/2016.
 */

public class NewsClient {

    private Context context;

    private static final String BaseUrl = "https://newsapi.org/v1/";

    public NewsClient (Context context){
        this.context = context;
    }

    public Retrofit getData = new Retrofit.Builder()
            .baseUrl(BaseUrl)
            .client(new OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
