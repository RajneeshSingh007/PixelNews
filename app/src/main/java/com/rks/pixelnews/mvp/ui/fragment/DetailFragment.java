package com.rks.pixelnews.mvp.ui.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rks.pixelnews.R;
import com.rks.pixelnews.mvp.data.model.Article;
import com.rks.pixelnews.mvp.data.model.News;
import com.rks.pixelnews.mvp.data.network.NewsClient;
import com.rks.pixelnews.mvp.data.network.NewsService;
import com.rks.pixelnews.mvp.presenter.FragmentPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFragment extends FragmentPresenter {


    private ImageView newsDetailImage;
    private TextView newsTitle,newsDetailDescription;

    @Override
    protected int setLayout() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void logic() {
        new getDetailTechNews().execute("executed");
    }

    @Override
    protected void ui(View view) {
        newsDetailImage = (ImageView) view.findViewById(R.id.DetailsnewsArtwork);
        newsTitle = (TextView) view.findViewById(R.id.news_title);
        newsDetailDescription = (TextView) view.findViewById(R.id.news_fulldescription);
    }

    @Override
    protected Fragment instancesfragments() {
        return new DetailFragment();
    }

    private class getDetailTechNews extends AsyncTask<String,String,List<Article>> {

        private ProgressDialog progressDialog;
        private Call<News> newsCall;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Tech News");
            progressDialog.setMessage("Please wait loading....");
            progressDialog.show();
        }

        @Override
        protected List<Article> doInBackground(String... params) {
            NewsClient newsClient = new NewsClient(getContext());
            NewsService newsService = newsClient.getData.create(NewsService.class);
            newsCall = newsService.getNews();
            return null;
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            super.onPostExecute(articles);
            newsCall.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    if (response.isSuccessful()){
                        News news = response.body();
                        if (news != null){
                            newsTitle.setText(news.getArticles().get(0).getTitle());
                            newsDetailDescription.setText(news.getArticles().get(0).getDescription());
                            Picasso.with(getContext())
                                    .load(news.getArticles().get(0).getUrlToImage())
                                    .placeholder(R.mipmap.ic_launcher)
                                    .error(R.mipmap.ic_launcher)
                                    .into(newsDetailImage);
                            progressDialog.dismiss();
                        }
                    }
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });
        }
    }
}
