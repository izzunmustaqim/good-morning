package com.applab.goodmorning.News.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.News.holder.NewsHolder;
import com.applab.goodmorning.News.model.News;
import com.applab.goodmorning.News.model.NewsItem;
import com.applab.goodmorning.News.model.NewsMediaList;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by user on 10-Mar-16.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<News> mNews = new ArrayList<>();

    public NewsAdapter(Context context, ArrayList<News> newses) {
        mContext = context;
        mNews = newses;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_news_row, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        holder.getTxtDate().setText(Utilities.setCalendarDate("yyyy-MM-dd", "MMM yy", mNews.get(position).getNewsDate()));
        holder.getTxtDescription().setText(mNews.get(position).getNewsDescription());
        holder.getTxtTitle().setText(mNews.get(position).getNewsTitle());
        holder.getTxtBottomDate().setText(Utilities.setCalendarDate("yyyy-MM-dd", "dd", mNews.get(position).getNewsDate()));
        Glide.with(mContext)
                .load(mNews.get(position).getCoverImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.empty_photo)
                .into(holder.getImgProducts());
    }

    @Override
    public int getItemCount() {
        return mNews == null ? 0 : mNews.size();
    }

    public void swapNews(ArrayList<News> mNews) {
        this.mNews = mNews;
        this.notifyDataSetChanged();
    }
}
