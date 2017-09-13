package com.applab.goodmorning.Gallery.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by user on 1/4/2016.
 */
public class PhotoItems implements Parcelable {
    @SerializedName("items")
    @Expose
    private ArrayList<Photo> items = new ArrayList<Photo>();

    private int position = 0;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return The items
     */
    public ArrayList<Photo> getPhotos() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setPhotos(ArrayList<Photo> items) {
        this.items = items;
    }

    public void readFromParcel(Parcel in) {
        this.items = in.readArrayList(Photo.class.getClassLoader());
        this.position = in.readInt();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(items);
        dest.writeInt(position);
    }

    public PhotoItems() {

    }

    public PhotoItems(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public PhotoItems createFromParcel(Parcel in) {
            return new PhotoItems(in);
        }

        @Override
        public PhotoItems[] newArray(int size) {
            return new PhotoItems[size];
        }
    };
}