package com.applab.goodmorning.Welcome.model;

/**
 * Created by user on 31/3/2016.
 */
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerItems implements Parcelable {
    @SerializedName("items")
    @Expose
    private ArrayList<Banner> items = new ArrayList<Banner>();

    /**
     * @return The items
     */
    public ArrayList<Banner> getBanners() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setBanners(ArrayList<Banner> items) {
        this.items = items;
    }

    public void readFromParcel(Parcel in) {
        this.items = in.readArrayList(Banner.class.getClassLoader());
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(items);
    }

    public BannerItems() {

    }

    public BannerItems(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public BannerItems createFromParcel(Parcel in) {
            return new BannerItems(in);
        }

        @Override
        public BannerItems[] newArray(int size) {
            return new BannerItems[size];
        }
    };
}