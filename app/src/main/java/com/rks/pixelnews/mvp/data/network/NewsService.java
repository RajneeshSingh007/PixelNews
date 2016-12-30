package com.rks.pixelnews.mvp.data.network;

import com.rks.pixelnews.mvp.data.model.News;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Coolalien on 12/5/2016.
 */

public interface NewsService {

    @GET("articles?source=techcrunch&sortBy=latest&apiKey=d0a3963038a24ffea6e9cf79a859fed4")
    Call<News> getNews();
}
