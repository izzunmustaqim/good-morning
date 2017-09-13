package com.applab.goodmorning.News.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.News.holder.NewsDetailsTextHolder;
import com.applab.goodmorning.News.model.News;
import com.applab.goodmorning.News.model.NewsItem;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter;
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder;

import java.util.ArrayList;

/**
 * Created by User on 5/4/2016.
 */
public class NewsDetailsTextBinder extends DataBinder<NewsDetailsTextHolder> {
    private News mNews;

    public NewsDetailsTextBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public NewsDetailsTextHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_news_details_row, parent, false);
        return new NewsDetailsTextHolder(view);
    }

    @Override
    public void bindViewHolder(NewsDetailsTextHolder holder, int position) {
        holder.getTxtDate().setText(Utilities.setCalendarDate("yyyy-MM-dd", "MMM yy", mNews.getNewsDate()));
        holder.getTxtBottomDate().setText(Utilities.setCalendarDate("yyyy-MM-dd", "dd", mNews.getNewsDate()));
        holder.getTxtTitle().setText(mNews.getNewsTitle());
        holder.getTxtDescription().setText(mNews.getNewsDescription());
    }

    @Override
    public int getItemCount() {
        return mNews == null ? 0 : 1;
    }

    public void addAll(News news) {
        this.mNews = news;
        this.notifyDataSetChanged();
    }

}
