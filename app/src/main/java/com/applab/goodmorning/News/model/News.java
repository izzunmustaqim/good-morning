package com.applab.goodmorning.News.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 09-Mar-16.
 */
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News implements Parcelable {

    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("NewsDate")
    @Expose
    private String NewsDate;
    @SerializedName("NewsTitle")
    @Expose
    private String NewsTitle;
    @SerializedName("NewsDescription")
    @Expose
    private String NewsDescription;
    @SerializedName("CoverImage")
    @Expose
    private String CoverImage;
    @SerializedName("Type")
    @Expose
    private String Type;
    @SerializedName("YoutubeUrl")
    @Expose
    private String YoutubeUrl;
    @SerializedName("YoutubeId")
    @Expose
    private String YoutubeId;
    @SerializedName("File")
    @Expose
    private String File;
    @SerializedName("CreateDate")
    @Expose
    private String CreateDate;
    @SerializedName("NewsMediaList")
    @Expose
    private List<NewsMediaList> media = new ArrayList<NewsMediaList>();

    /**
     * @return The Id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * @param Id The Id
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     * @return The NewsDate
     */
    public String getNewsDate() {
        return NewsDate;
    }

    /**
     * @param NewsDate The NewsDate
     */
    public void setNewsDate(String NewsDate) {
        this.NewsDate = NewsDate;
    }

    /**
     * @return The NewsTitle
     */
    public String getNewsTitle() {
        return NewsTitle;
    }

    /**
     * @param NewsTitle The NewsTitle
     */
    public void setNewsTitle(String NewsTitle) {
        this.NewsTitle = NewsTitle;
    }

    /**
     * @return The NewsDescription
     */
    public String getNewsDescription() {
        return NewsDescription;
    }

    /**
     * @param NewsDescription The NewsDescription
     */
    public void setNewsDescription(String NewsDescription) {
        this.NewsDescription = NewsDescription;
    }

    /**
     * @return The CoverImage
     */
    public String getCoverImage() {
        return CoverImage;
    }

    /**
     * @param CoverImage The CoverImage
     */
    public void setCoverImage(String CoverImage) {
        this.CoverImage = CoverImage;
    }

    /**
     * @return The Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type The Type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

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

    /**
     * @return The File
     */
    public String getFile() {
        return File;
    }

    /**
     * @param File The File
     */
    public void setFile(String File) {
        this.File = File;
    }

    /**
     * @return The CreateDate
     */
    public String getCreateDate() {
        return CreateDate;
    }

    /**
     * @param CreateDate The CreateDate
     */
    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    /**
     * @return The media
     */
    public List<NewsMediaList> getmedia() {
        return media;
    }

    /**
     * @param media The media
     */
    public void setmedia(List<NewsMediaList> media) {
        this.media = media;
    }

    public News() {

    }

    public News(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.Id = in.readInt();
        this.NewsDate = in.readString();
        this.NewsTitle = in.readString();
        this.NewsDescription = in.readString();
        this.CoverImage = in.readString();
        this.Type = in.readString();
        this.YoutubeId = in.readString();
        this.YoutubeUrl = in.readString();
        this.File = in.readString();
        this.CreateDate = in.readString();
        this.media = in.readArrayList(NewsMediaList.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.NewsDate);
        dest.writeString(this.NewsTitle);
        dest.writeString(this.NewsDescription);
        dest.writeString(this.CoverImage);
        dest.writeString(this.Type);
        dest.writeString(this.YoutubeId);
        dest.writeString(this.YoutubeUrl);
        dest.writeString(this.File);
        dest.writeString(this.CreateDate);
        dest.writeList(this.media);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}