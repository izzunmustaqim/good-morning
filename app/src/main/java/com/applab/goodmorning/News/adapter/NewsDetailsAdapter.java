package com.applab.goodmorning.News.adapter;

import android.content.Context;

import com.applab.goodmorning.News.binder.NewsDetailsBinder;
import com.applab.goodmorning.News.binder.NewsDetailsTextBinder;
import com.applab.goodmorning.News.model.News;
import com.yqritc.recyclerviewmultipleviewtypesadapter.EnumMapBindAdapter;

import java.util.ArrayList;

/**
 * Created by User on 5/4/2016.
 */
public class NewsDetailsAdapter extends EnumMapBindAdapter<NewsDetailsAdapter.NewsViewType> {

    private Context mContext;

    enum NewsViewType {
        TEXTDETAILS, PHOTODETAILS
    }

    public NewsDetailsAdapter(Context context) {
        this.mContext = context;
        putBinder(NewsViewType.TEXTDETAILS, new NewsDetailsTextBinder(this));
        putBinder(NewsViewType.PHOTODETAILS, new NewsDetailsBinder(this,context));
    }

    public void setNewsData(News mNews) {
        ((NewsDetailsTextBinder) getDataBinder(NewsViewType.TEXTDETAILS)).addAll(mNews);
        ((NewsDetailsBinder)getDataBinder(NewsViewType.PHOTODETAILS)).addAll(mNews);
    }

    @Override
    public NewsViewType getEnumFromPosition(int position) {
        if (position == 0) {
            return NewsViewType.TEXTDETAILS;
        } else {
            return NewsViewType.PHOTODETAILS;
        }
    }

    @Override
    public NewsViewType getEnumFromOrdinal(int ordinal) {
        return NewsViewType.values()[ordinal];
    }
}
