package com.rks.pixelnews.mvp.ui.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rks.pixelnews.R;
import com.rks.pixelnews.mvp.data.model.Article;
import com.rks.pixelnews.mvp.data.model.News;
import com.rks.pixelnews.mvp.data.network.NewsClient;
import com.rks.pixelnews.mvp.data.network.NewsService;
import com.rks.pixelnews.mvp.presenter.AdapterPresenter;
import com.rks.pixelnews.mvp.presenter.FragmentPresenter;
import com.rks.pixelnews.mvp.ui.activity.MainActivity;
import com.rks.pixelnews.mvp.ui.adapters.TechNewsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends FragmentPresenter {

    private Toolbar toolbar;
    private RecyclerView technewsRv;
    private TechNewsAdapter techNewsAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void logic() {
        DrawerLayout drawerLayout = ((MainActivity) getActivity()).getDrawerLayout();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        new getTechNews().execute("Executed");
    }

    @Override
    protected void ui(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        technewsRv = (RecyclerView) view.findViewById(R.id.technews_rv);
    }

    @Override
    public Fragment instancesfragments() {
        return new MainFragment();
    }


    private class getTechNews extends AsyncTask<String,String,List<Article>>{

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
                        final News news = response.body();
                        if (news != null){
                            techNewsAdapter = new TechNewsAdapter(getContext(), news.getArticles());
                            technewsRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                            technewsRv.setHasFixedSize(true);
                            technewsRv.setAdapter(techNewsAdapter);
                            progressDialog.dismiss();
                            techNewsAdapter.setOnItemClickListener(new AdapterPresenter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position, View view) {
                                    switch (view.getId()){
                                        case R.id.newsArtwork:
                                            getFragmentManager().beginTransaction().replace(R.id.fragcontainer,new DetailFragment().instancesfragments()).addToBackStack(null).commit();
                                            break;
                                    }
                                }
                            });
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
