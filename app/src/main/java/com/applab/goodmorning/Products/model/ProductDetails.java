package com.applab.goodmorning.Products.model;

/**
 * Created by user on 29-Mar-16.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails implements Parcelable {

    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("ProductTitle")
    @Expose
    private String ProductTitle;
    @SerializedName("ProductSubTitle")
    @Expose
    private String ProductSubTitle;
    @SerializedName("ProductDescription")
    @Expose
    private String ProductDescription;
    @SerializedName("Price")
    @Expose
    private String Price;
    @SerializedName("ProductImageList")
    @Expose
    private List<Object> ProductImageList = new ArrayList<Object>();
    @SerializedName("IsPromotion")
    @Expose
    private Integer IsPromotion;
    @SerializedName("PromotionPrice")
    @Expose
    private String PromotionPrice;
    @SerializedName("CreateDate")
    @Expose
    private String CreateDate;

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
     * @return The ProductTitle
     */
    public String getProductTitle() {
        return ProductTitle;
    }

    /**
     * @param ProductTitle The ProductTitle
     */
    public void setProductTitle(String ProductTitle) {
        this.ProductTitle = ProductTitle;
    }

    /**
     * @return The ProductSubTitle
     */
    public String getProductSubTitle() {
        return ProductSubTitle;
    }

    /**
     * @param ProductSubTitle The ProductSubTitle
     */
    public void setProductSubTitle(String ProductSubTitle) {
        this.ProductSubTitle = ProductSubTitle;
    }

    /**
     * @return The ProductDescription
     */
    public String getProductDescription() {
        return ProductDescription;
    }

    /**
     * @param ProductDescription The ProductDescription
     */
    public void setProductDescription(String ProductDescription) {
        this.ProductDescription = ProductDescription;
    }

    /**
     * @return The Price
     */
    public String getPrice() {
        return Price;
    }

    /**
     * @param Price The Price
     */
    public void setPrice(String Price) {
        this.Price = Price;
    }

    /**
     * @return The ProductImageList
     */
    public List<Object> getProductImageList() {
        return ProductImageList;
    }

    /**
     * @param ProductImageList The ProductImageList
     */
    public void setProductImageList(List<Object> ProductImageList) {
        this.ProductImageList = ProductImageList;
    }

    /**
     * @return The IsPromotion
     */
    public Integer getIsPromotion() {
        return IsPromotion;
    }

    /**
     * @param IsPromotion The IsPromotion
     */
    public void setIsPromotion(Integer IsPromotion) {
        this.IsPromotion = IsPromotion;
    }

    /**
     * @return The PromotionPrice
     */
    public String getPromotionPrice() {
        return PromotionPrice;
    }

    /**
     * @param PromotionPrice The PromotionPrice
     */
    public void setPromotionPrice(String PromotionPrice) {
        this.PromotionPrice = PromotionPrice;
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

    public ProductDetails() {

    }

    public ProductDetails(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.Id = in.readInt();
        this.ProductTitle = in.readString();
        this.ProductSubTitle = in.readString();
        this.ProductDescription = in.readString();
        this.Price = in.readString();
        this.IsPromotion = in.readInt();
        this.CreateDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.ProductTitle);
        dest.writeString(this.ProductSubTitle);
        dest.writeString(this.ProductDescription);
        dest.writeString(this.PromotionPrice);
        dest.writeInt(this.IsPromotion);
        dest.writeString(this.CreateDate);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public ProductDetails createFromParcel(Parcel in) {
            return new ProductDetails(in);
        }

        @Override
        public ProductDetails[] newArray(int size) {
            return new ProductDetails[size];
        }
    };
}