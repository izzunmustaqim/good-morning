package com.applab.goodmorning.Gallery.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 10-Mar-16.
 */
public class Gallery implements Parcelable {
    private String images;
    private String playerID;

    public Gallery() {

    }

    public Gallery(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.images = in.readString();
        this.playerID = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.images);
        dest.writeString(this.playerID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Gallery createFromParcel(Parcel in) {
            return new Gallery(in);
        }

        @Override
        public Gallery[] newArray(int size) {
            return new Gallery[size];
        }
    };

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }
}
