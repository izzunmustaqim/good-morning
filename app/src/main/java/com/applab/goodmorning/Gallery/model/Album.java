package com.applab.goodmorning.Gallery.model;

/**
 * Created by user on 1/4/2016.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album implements Parcelable {

    @SerializedName("AlbumId")
    @Expose
    private Integer AlbumId;
    @SerializedName("CoverPhoto")
    @Expose
    private String CoverPhoto;
    @SerializedName("AlbumTitle")
    @Expose
    private String AlbumTitle;
    @SerializedName("PhotoCount")
    @Expose
    private Integer PhotoCount;

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
     * The CoverPhoto
     */
    public String getCoverPhoto() {
        return CoverPhoto;
    }

    /**
     *
     * @param CoverPhoto
     * The CoverPhoto
     */
    public void setCoverPhoto(String CoverPhoto) {
        this.CoverPhoto = CoverPhoto;
    }

    /**
     *
     * @return
     * The AlbumTitle
     */
    public String getAlbumTitle() {
        return AlbumTitle;
    }

    /**
     *
     * @param AlbumTitle
     * The AlbumTitle
     */
    public void setAlbumTitle(String AlbumTitle) {
        this.AlbumTitle = AlbumTitle;
    }

    /**
     *
     * @return
     * The PhotoCount
     */
    public Integer getPhotoCount() {
        return PhotoCount;
    }

    /**
     *
     * @param PhotoCount
     * The PhotoCount
     */
    public void setPhotoCount(Integer PhotoCount) {
        this.PhotoCount = PhotoCount;
    }
    public void readFromParcel(Parcel in) {
        this.AlbumId = in.readInt();
        this.CoverPhoto = in.readString();
        this.AlbumTitle = in.readString();
        this.PhotoCount = in.readInt();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(AlbumId);
        dest.writeString(CoverPhoto);
        dest.writeString(AlbumTitle);
        dest.writeInt(PhotoCount);
    }

    public Album() {

    }

    public Album(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
}