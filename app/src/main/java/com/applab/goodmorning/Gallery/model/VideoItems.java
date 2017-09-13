package com.applab.goodmorning.Gallery.model;

/**
 * Created by user on 30/3/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoItems implements Parcelable {
    @SerializedName("items")
    @Expose
    private ArrayList<Video> items = new ArrayList<Video>();

    /**
     * @return The items
     */
    public ArrayList<Video> getVideos() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setVideos(ArrayList<Video> items) {
        this.items = items;
    }

    public void readFromParcel(Parcel in) {
        this.items = in.readArrayList(Video.class.getClassLoader());
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(items);
    }

    public VideoItems() {

    }

    public VideoItems(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public VideoItems createFromParcel(Parcel in) {
            return new VideoItems(in);
        }

        @Override
        public VideoItems[] newArray(int size) {
            return new VideoItems[size];
        }
    };
}