package com.applab.goodmorning.News.model;

/**
 * Created by User on 4/4/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsItem implements Parcelable {


    @SerializedName("items")
    @Expose
    private List<News> News;


    /**
     * @return The News
     */
    public List<News> getNews() {
        return News;
    }

    /**
     * @param News The News
     */
    public void setNews(List<News> News) {
        this.News = News;
    }

    public NewsItem() {

    }

    public NewsItem(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.News = in.readArrayList(News.getClass().getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.News);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public NewsItem createFromParcel(Parcel in) {
            return new NewsItem(in);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };

}
