package com.applab.goodmorning.Promotions.model;

/**
 * Created by User on 4/11/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 11/4/2016
 * -- Description: Promotion .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */


public class Promotion implements Parcelable {

    @SerializedName("PromotionId")
    @Expose
    private Integer PromotionId;
    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("Image")
    @Expose
    private String Image;
    @SerializedName("File")
    @Expose
    private String File;
    @SerializedName("FromDate")
    @Expose
    private String FromDate;
    @SerializedName("ToDate")
    @Expose
    private String ToDate;

    /**
     *
     * @return
     * The PromotionId
     */
    public Integer getPromotionId() {
        return PromotionId;
    }

    /**
     *
     * @param PromotionId
     * The PromotionId
     */
    public void setPromotionId(Integer PromotionId) {
        this.PromotionId = PromotionId;
    }

    /**
     *
     * @return
     * The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param Title
     * The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     *
     * @return
     * The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @param Description
     * The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     *
     * @return
     * The Image
     */
    public String getImage() {
        return Image;
    }

    /**
     *
     * @param Image
     * The Image
     */
    public void setImage(String Image) {
        this.Image = Image;
    }

    /**
     *
     * @return
     * The File
     */
    public String getFile() {
        return File;
    }

    /**
     *
     * @param File
     * The File
     */
    public void setFile(String File) {
        this.File = File;
    }

    /**
     *
     * @return
     * The FromDate
     */
    public String getFromDate() {
        return FromDate;
    }

    /**
     *
     * @param FromDate
     * The FromDate
     */
    public void setFromDate(String FromDate) {
        this.FromDate = FromDate;
    }

    /**
     *
     * @return
     * The ToDate
     */
    public String getToDate() {
        return ToDate;
    }

    /**
     *
     * @param ToDate
     * The ToDate
     */
    public void setToDate(String ToDate) {
        this.ToDate = ToDate;
    }


    public Promotion() {
    }

    public Promotion(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.PromotionId = in.readInt();
        this.Title = in.readString();
        this.Description = in.readString();
        this.Image = in.readString();
        this.File = in.readString();
        this.FromDate = in.readString();
        this.ToDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.PromotionId);
        dest.writeString(this.Title);
        dest.writeString(this.Description);
        dest.writeString(this.Image);
        dest.writeString(this.File);
        dest.writeString(this.FromDate);
        dest.writeString(this.ToDate);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Promotion createFromParcel(Parcel in) {
            return new Promotion(in);
        }

        @Override
        public Promotion[] newArray(int size) {
            return new Promotion[size];
        }
    };
}
