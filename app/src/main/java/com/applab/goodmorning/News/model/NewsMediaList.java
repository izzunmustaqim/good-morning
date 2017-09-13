package com.applab.goodmorning.News.model;

/**
 * Created by User on 4/4/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsMediaList implements Parcelable {

    @SerializedName("NewsId")
    @Expose
    private Integer NewsId;
    @SerializedName("Image")
    @Expose
    private String Image;

    /**
     * @return The NewsId
     */
    public Integer getNewsId() {
        return NewsId;
    }

    /**
     * @param NewsId The NewsId
     */
    public void setNewsId(Integer NewsId) {
        this.NewsId = NewsId;
    }

    /**
     * @return The Image
     */
    public String getImage() {
        return Image;
    }

    /**
     * @param Image The Image
     */
    public void setImage(String Image) {
        this.Image = Image;
    }

    public NewsMediaList() {
    }

    public NewsMediaList(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.NewsId = in.readInt();
        this.Image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.NewsId);
        dest.writeString(this.Image);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public NewsMediaList createFromParcel(Parcel in) {
            return new NewsMediaList(in);
        }

        @Override
        public NewsMediaList[] newArray(int size) {
            return new NewsMediaList[size];
        }
    };
}
