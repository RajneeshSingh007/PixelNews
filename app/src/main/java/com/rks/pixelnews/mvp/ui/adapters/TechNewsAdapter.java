package com.rks.pixelnews.mvp.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rks.pixelnews.R;
import com.rks.pixelnews.mvp.data.model.Article;
import com.rks.pixelnews.mvp.presenter.AdapterPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Coolalien on 12/5/2016.
 */

public class TechNewsAdapter extends AdapterPresenter<Article, TechNewsAdapter.TechNewsHolder> {

    public TechNewsAdapter(@NonNull Context context, @NonNull List<Article> data) {
        super(context, data);
    }

    @Override
    public TechNewsAdapter.TechNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.technews_list,null);
        return new TechNewsHolder(root);
    }

    @Override
    public void onBindViewHolder(TechNewsAdapter.TechNewsHolder holder, int position) {
        Article article = getItem(position);
        holder.newsInfo.setText(article.getDescription());
        holder.newsTitle.setText(article.getTitle());
        Picasso.with(getContext())
                .load(article.getUrlToImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.newsImage);
    }

    @Override
    public Article getItem(int position) throws ArrayIndexOutOfBoundsException {
        return super.getItem(position);
    }

    public class TechNewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView newsImage;
        private TextView newsTitle, newsInfo;

        public TechNewsHolder(View itemView) {
            super(itemView);

            newsImage = (ImageView) itemView.findViewById(R.id.newsArtwork);
            newsTitle = (TextView) itemView.findViewById(R.id.news_title);
            newsInfo = (TextView) itemView.findViewById(R.id.news_info);
            itemView.setOnClickListener(this);
            newsImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            triggerOnItemClickListener(pos,v);
        }
    }
}
