package com.applab.goodmorning.Gallery.model;

/**
 * Created by user on 30/3/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video implements Parcelable{

    @SerializedName("YoutubeUrl")
    @Expose
    private String YoutubeUrl;
    @SerializedName("YoutubeId")
    @Expose
    private String YoutubeId;

    /**
     * @return The YoutubeUrl
     */
    public String getYoutubeUrl() {
        return YoutubeUrl;
    }

    /**
     * @param YoutubeUrl The YoutubeUrl
     */
    public void setYoutubeUrl(String YoutubeUrl) {
        this.YoutubeUrl = YoutubeUrl;
    }

    /**
     * @return The YoutubeId
     */
    public String getYoutubeId() {
        return YoutubeId;
    }

    /**
     * @param YoutubeId The YoutubeId
     */
    public void setYoutubeId(String YoutubeId) {
        this.YoutubeId = YoutubeId;
    }

    public void readFromParcel(Parcel in) {
        this.YoutubeUrl = in.readString();
        this.YoutubeId = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(YoutubeUrl);
        dest.writeString(YoutubeId);
    }

    public Video() {

    }

    public Video(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
