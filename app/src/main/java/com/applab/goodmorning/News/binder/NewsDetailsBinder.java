package com.applab.goodmorning.News.binder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.Image.activity.ImageSlidingActivty;
import com.applab.goodmorning.News.holder.NewsDetailsHolder;
import com.applab.goodmorning.News.model.News;
import com.applab.goodmorning.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter;
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder;

import java.util.ArrayList;

/**
 * Created by User on 5/4/2016.
 */
public class NewsDetailsBinder extends DataBinder<NewsDetailsHolder> {
    private News mNews;
    private Context mContext;

    public NewsDetailsBinder(DataBindAdapter dataBinderAdapter, Context context) {
        super(dataBinderAdapter);
        this.mContext = context;
    }

    @Override
    public NewsDetailsHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_news_picture, parent, false);
        return new NewsDetailsHolder(view);
    }

    @Override
    public void bindViewHolder(NewsDetailsHolder holder, final int position) {
        Glide.with(holder.getImgProducts().getContext())
                .load(mNews.getmedia().get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.empty_photo)
                .into(holder.getImgProducts());

        holder.getImgProducts().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImageSlidingActivty.class);
                ArrayList<String> arr = new ArrayList<>();
                arr.add(mNews.getmedia().get(position).getImage());
                intent.putExtra("url", arr);
                intent.putExtra("position", 0);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNews == null ? 0 : mNews.getmedia() == null ? 0 : mNews.getmedia().size();
    }

    public void addAll(News news) {
        this.mNews = news;
        this.notifyDataSetChanged();
    }
}
