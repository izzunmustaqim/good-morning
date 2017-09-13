package com.applab.goodmorning.Checkout.model;

/**
 * Created by user on 14/4/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckOut implements Parcelable {

    @SerializedName("FirstName")
    @Expose
    private String FirstName;
    @SerializedName("LastName")
    @Expose
    private String LastName;
    @SerializedName("Company")
    @Expose
    private String Company;
    @SerializedName("ContactNo")
    @Expose
    private String ContactNo;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("Address")
    @Expose
    private String Address;
    @SerializedName("City")
    @Expose
    private String City;
    @SerializedName("ZipCode")
    @Expose
    private String ZipCode;
    @SerializedName("StateCode")
    @Expose
    private String StateCode;
    @SerializedName("CountryId")
    @Expose
    private String CountryId;
    @SerializedName("PromotionalCode")
    @Expose
    private String PromotionalCode;
    @SerializedName("ShippingType")
    @Expose
    private String ShippingType;
    @SerializedName("ShippingFee")
    @Expose
    private String ShippingFee;
    @SerializedName("Gst")
    @Expose
    private String Gst;
    @SerializedName("GstPrice")
    @Expose
    private String GstPrice;
    @SerializedName("TotalPrice")
    @Expose
    private String TotalPrice;
    @SerializedName("TotalPriceIncGst")
    @Expose
    private String TotalPriceIncGst;
    private String currency;
    @SerializedName("Remarks")
    @Expose
    private String Remarks;
    private String PromotionValue;

    public String getPromotionValue() {
        return PromotionValue;
    }

    public void setPromotionValue(String promotionValue) {
        PromotionValue = promotionValue;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    /**
     * @return The FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * @param FirstName The FirstName
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * @return The LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * @param LastName The LastName
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * @return The Company
     */
    public String getCompany() {
        return Company;
    }

    /**
     * @param Company The Company
     */
    public void setCompany(String Company) {
        this.Company = Company;
    }

    /**
     * @return The ContactNo
     */
    public String getContactNo() {
        return ContactNo;
    }

    /**
     * @param ContactNo The ContactNo
     */
    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
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
     * @return The City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param City The City
     */
    public void setCity(String City) {
        this.City = City;
    }

    /**
     * @return The ZipCode
     */
    public String getZipCode() {
        return ZipCode;
    }

    /**
     * @param ZipCode The ZipCode
     */
    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

    /**
     * @return The StateCode
     */
    public String getStateCode() {
        return StateCode;
    }

    /**
     * @param StateCode The StateCode
     */
    public void setStateCode(String StateCode) {
        this.StateCode = StateCode;
    }

    /**
     * @return The CountryId
     */
    public String getCountryId() {
        return CountryId;
    }

    /**
     * @param CountryId The CountryId
     */
    public void setCountryId(String CountryId) {
        this.CountryId = CountryId;
    }

    /**
     * @return The PromotionalCode
     */
    public String getPromotionalCode() {
        return PromotionalCode;
    }

    /**
     * @param PromotionalCode The PromotionalCode
     */
    public void setPromotionalCode(String PromotionalCode) {
        this.PromotionalCode = PromotionalCode;
    }

    /**
     * @return The ShippingType
     */
    public String getShippingType() {
        return ShippingType;
    }

    /**
     * @param ShippingType The ShippingType
     */
    public void setShippingType(String ShippingType) {
        this.ShippingType = ShippingType;
    }

    /**
     * @return The ShippingFee
     */
    public String getShippingFee() {
        return ShippingFee;
    }

    /**
     * @param ShippingFee The ShippingFee
     */
    public void setShippingFee(String ShippingFee) {
        this.ShippingFee = ShippingFee;
    }

    /**
     * @return The Gst
     */
    public String getGst() {
        return Gst;
    }

    /**
     * @param Gst The Gst
     */
    public void setGst(String Gst) {
        this.Gst = Gst;
    }

    /**
     * @return The GstPrice
     */
    public String getGstPrice() {
        return GstPrice;
    }

    /**
     * @param GstPrice The GstPrice
     */
    public void setGstPrice(String GstPrice) {
        this.GstPrice = GstPrice;
    }

    /**
     * @return The TotalPrice
     */
    public String getTotalPrice() {
        return TotalPrice;
    }

    /**
     * @param TotalPrice The TotalPrice
     */
    public void setTotalPrice(String TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    /**
     * @return The TotalPriceIncGst
     */
    public String getTotalPriceIncGst() {
        return TotalPriceIncGst;
    }

    /**
     * @param TotalPriceIncGst The TotalPriceIncGst
     */
    public void setTotalPriceIncGst(String TotalPriceIncGst) {
        this.TotalPriceIncGst = TotalPriceIncGst;
    }


    public CheckOut() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.FirstName);
        dest.writeString(this.LastName);
        dest.writeString(this.Company);
        dest.writeString(this.ContactNo);
        dest.writeString(this.Email);
        dest.writeString(this.Address);
        dest.writeString(this.City);
        dest.writeString(this.ZipCode);
        dest.writeString(this.StateCode);
        dest.writeString(this.CountryId);
        dest.writeString(this.PromotionalCode);
        dest.writeString(this.ShippingType);
        dest.writeString(this.ShippingFee);
        dest.writeString(this.Gst);
        dest.writeString(this.GstPrice);
        dest.writeString(this.TotalPrice);
        dest.writeString(this.TotalPriceIncGst);
        dest.writeString(this.currency);
        dest.writeString(this.Remarks);
        dest.writeString(this.PromotionValue);
    }

    protected CheckOut(Parcel in) {
        this.FirstName = in.readString();
        this.LastName = in.readString();
        this.Company = in.readString();
        this.ContactNo = in.readString();
        this.Email = in.readString();
        this.Address = in.readString();
        this.City = in.readString();
        this.ZipCode = in.readString();
        this.StateCode = in.readString();
        this.CountryId = in.readString();
        this.PromotionalCode = in.readString();
        this.ShippingType = in.readString();
        this.ShippingFee = in.readString();
        this.Gst = in.readString();
        this.GstPrice = in.readString();
        this.TotalPrice = in.readString();
        this.TotalPriceIncGst = in.readString();
        this.currency = in.readString();
        this.Remarks = in.readString();
        this.PromotionValue = in.readString();
    }

    public static final Creator<CheckOut> CREATOR = new Creator<CheckOut>() {
        @Override
        public CheckOut createFromParcel(Parcel source) {
            return new CheckOut(source);
        }

        @Override
        public CheckOut[] newArray(int size) {
            return new CheckOut[size];
        }
    };
}