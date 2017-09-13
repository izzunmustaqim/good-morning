package com.applab.goodmorning.Welcome.model;

/**
 * Created by user on 31/3/2016.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Banner implements Parcelable{

    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("DesktopImageUrl")
    @Expose
    private String DesktopImageUrl;
    @SerializedName("MobileImageUrl")
    @Expose
    private String MobileImageUrl;

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
     * The DesktopImageUrl
     */
    public String getDesktopImageUrl() {
        return DesktopImageUrl;
    }

    /**
     *
     * @param DesktopImageUrl
     * The DesktopImageUrl
     */
    public void setDesktopImageUrl(String DesktopImageUrl) {
        this.DesktopImageUrl = DesktopImageUrl;
    }

    /**
     *
     * @return
     * The MobileImageUrl
     */
    public String getMobileImageUrl() {
        return MobileImageUrl;
    }

    /**
     *
     * @param MobileImageUrl
     * The MobileImageUrl
     */
    public void setMobileImageUrl(String MobileImageUrl) {
        this.MobileImageUrl = MobileImageUrl;
    }

    public void readFromParcel(Parcel in) {
        this.Title = in.readString();
        this.Description = in.readString();
        this.DesktopImageUrl = in.readString();
        this.MobileImageUrl = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Description);
        dest.writeString(DesktopImageUrl);
        dest.writeString(MobileImageUrl);
    }

    public Banner() {

    }

    public Banner(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Banner createFromParcel(Parcel in) {
            return new Banner(in);
        }

        @Override
        public Banner[] newArray(int size) {
            return new Banner[size];
        }
    };

}