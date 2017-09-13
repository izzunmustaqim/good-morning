package com.applab.goodmorning.Gallery.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 1/4/2016.
 */
public class Photo implements Parcelable {

    @SerializedName("AlbumId")
    @Expose
    private Integer AlbumId;
    @SerializedName("Photo")
    @Expose
    private String Photo;

    /**
     *
     * @return
     * The AlbumId
     */
    public Integer getAlbumId() {
        return AlbumId;
    }

    /**
     *
     * @param AlbumId
     * The AlbumId
     */
    public void setAlbumId(Integer AlbumId) {
        this.AlbumId = AlbumId;
    }

    /**
     *
     * @return
     * The Photo
     */
    public String getPhoto() {
        return Photo;
    }

    /**
     *
     * @param Photo
     * The Photo
     */
    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public void readFromParcel(Parcel in) {
        this.AlbumId = in.readInt();
        this.Photo = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(AlbumId);
        dest.writeString(Photo);
    }

    public Photo() {

    }

    public Photo(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}