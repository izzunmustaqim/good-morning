package com.applab.goodmorning.ContactUs.model;

/**
 * Created by user on 8/4/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUs implements Parcelable {

    @SerializedName("OutletTitle")
    @Expose
    private String OutletTitle;
    @SerializedName("OutletSubTitle")
    @Expose
    private String OutletSubTitle;
    @SerializedName("Latitude")
    @Expose
    private String Latitude;
    @SerializedName("Longitude")
    @Expose
    private String Longitude;
    @SerializedName("Address")
    @Expose
    private String Address;
    @SerializedName("TelNo")
    @Expose
    private String TelNo;
    @SerializedName("MobileNo")
    @Expose
    private String MobileNo;
    @SerializedName("Careline")
    @Expose
    private String Careline;
    @SerializedName("Fax")
    @Expose
    private String Fax;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("Website")
    @Expose
    private String Website;
    @SerializedName("OperationHour")
    @Expose
    private String OperationHour;

    /**
     * @return The OutletTitle
     */
    public String getOutletTitle() {
        return OutletTitle;
    }

    /**
     * @param OutletTitle The OutletTitle
     */
    public void setOutletTitle(String OutletTitle) {
        this.OutletTitle = OutletTitle;
    }

    /**
     * @return The OutletSubTitle
     */
    public String getOutletSubTitle() {
        return OutletSubTitle;
    }

    /**
     * @param OutletSubTitle The OutletSubTitle
     */
    public void setOutletSubTitle(String OutletSubTitle) {
        this.OutletSubTitle = OutletSubTitle;
    }

    /**
     * @return The Latitude
     */
    public String getLatitude() {
        return Latitude;
    }

    /**
     * @param Latitude The Latitude
     */
    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    /**
     * @return The Longitude
     */
    public String getLongitude() {
        return Longitude;
    }

    /**
     * @param Longitude The Longitude
     */
    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    /**
     * @return The Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address The Address
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return The TelNo
     */
    public String getTelNo() {
        return TelNo;
    }

    /**
     * @param TelNo The TelNo
     */
    public void setTelNo(String TelNo) {
        this.TelNo = TelNo;
    }

    /**
     * @return The MobileNo
     */
    public String getMobileNo() {
        return MobileNo;
    }

    /**
     * @param MobileNo The MobileNo
     */
    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }

    /**
     * @return The Careline
     */
    public String getCareline() {
        return Careline;
    }

    /**
     * @param Careline The Careline
     */
    public void setCareline(String Careline) {
        this.Careline = Careline;
    }

    /**
     * @return The Fax
     */
    public String getFax() {
        return Fax;
    }

    /**
     * @param Fax The Fax
     */
    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    /**
     * @return The Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email The Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return The Website
     */
    public String getWebsite() {
        return Website;
    }

    /**
     * @param Website The Website
     */
    public void setWebsite(String Website) {
        this.Website = Website;
    }

    /**
     * @return The OperationHour
     */
    public String getOperationHour() {
        return OperationHour;
    }

    /**
     * @param OperationHour The OperationHour
     */
    public void setOperationHour(String OperationHour) {
        this.OperationHour = OperationHour;
    }

    public void readFromParcel(Parcel in) {
        this.OutletTitle = in.readString();
        this.OutletSubTitle = in.readString();
        this.Latitude = in.readString();
        this.Longitude = in.readString();
        this.Address = in.readString();
        this.TelNo = in.readString();
        this.MobileNo = in.readString();
        this.Careline = in.readString();
        this.Fax = in.readString();
        this.Email = in.readString();
        this.Website = in.readString();
        this.OperationHour = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(OutletTitle);
        dest.writeString(OutletSubTitle);
        dest.writeString(Latitude);
        dest.writeString(Longitude);
        dest.writeString(Address);
        dest.writeString(TelNo);
        dest.writeString(MobileNo);
        dest.writeString(Careline);
        dest.writeString(Fax);
        dest.writeString(Email);
        dest.writeString(Website);
        dest.writeString(OperationHour);
    }

    public ContactUs() {

    }

    public ContactUs(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public ContactUs createFromParcel(Parcel in) {
            return new ContactUs(in);
        }

        @Override
        public ContactUs[] newArray(int size) {
            return new ContactUs[size];
        }
    };

}