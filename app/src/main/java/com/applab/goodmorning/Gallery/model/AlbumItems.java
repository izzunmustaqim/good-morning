package com.applab.goodmorning.Gallery.model;

/**
 * Created by user on 1/4/2016.
 */
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumItems implements Parcelable {
    @SerializedName("items")
    @Expose
    private ArrayList<Album> items = new ArrayList<Album>();

    /**
     * @return The items
     */
    public ArrayList<Album> getAlbums() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setAlbums(ArrayList<Album> items) {
        this.items = items;
    }

    public void readFromParcel(Parcel in) {
        this.items = in.readArrayList(Album.class.getClassLoader());
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(items);
    }

    public AlbumItems() {

    }

    public AlbumItems(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public AlbumItems createFromParcel(Parcel in) {
            return new AlbumItems(in);
        }

        @Override
        public AlbumItems[] newArray(int size) {
            return new AlbumItems[size];
        }
    };
}