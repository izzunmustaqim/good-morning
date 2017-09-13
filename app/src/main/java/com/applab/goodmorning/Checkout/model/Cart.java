package com.applab.goodmorning.Checkout.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.applab.goodmorning.Products.model.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 9/3/2016
 * -- Description: CartActivity .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class Cart implements Parcelable {
    @SerializedName("Products")
    @Expose
    private List<Product> Products = new ArrayList<Product>();
    @SerializedName("PromotionalCode")
    @Expose
    private String PromotionalCode;
    @SerializedName("PromotionalValue")
    @Expose
    private Double PromotionalValue;
    @SerializedName("TotalPrice")
    @Expose
    private Double TotalPrice;
    @SerializedName("ShippingType")
    @Expose
    private String ShippingType;
    @SerializedName("ShippingFee")
    @Expose
    private Double ShippingFee;
    @SerializedName("GstText")
    @Expose
    private Double GstText;
    @SerializedName("GstPrice")
    @Expose
    private Double GstPrice;
    @SerializedName("TotalPriceIncGstPrice")
    @Expose
    private Double TotalPriceIncGstPrice;
    @SerializedName("Currency")
    @Expose
    private String Currency;
    @SerializedName("Count")
    @Expose
    private Integer Count;

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    /**
     * @return The Products
     */
    public List<Product> getProducts() {
        return Products;
    }

    /**
     * @param Products The Products
     */
    public void setProducts(List<Product> Products) {
        this.Products = Products;
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
     * @return The PromotionalValue
     */
    public Double getPromotionalValue() {
        return PromotionalValue;
    }

    /**
     * @param PromotionalValue The PromotionalValue
     */
    public void setPromotionalValue(Double PromotionalValue) {
        this.PromotionalValue = PromotionalValue;
    }

    /**
     * @return The TotalPrice
     */
    public Double getTotalPrice() {
        return TotalPrice;
    }

    /**
     * @param TotalPrice The TotalPrice
     */
    public void setTotalPrice(Double TotalPrice) {
        this.TotalPrice = TotalPrice;
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
    public Double getShippingFee() {
        return ShippingFee;
    }

    /**
     * @param ShippingFee The ShippingFee
     */
    public void setShippingFee(Double ShippingFee) {
        this.ShippingFee = ShippingFee;
    }

    /**
     * @return The GstText
     */
    public Double getGstText() {
        return GstText;
    }

    /**
     * @param GstText The GstText
     */
    public void setGstText(Double GstText) {
        this.GstText = GstText;
    }

    /**
     * @return The GstPrice
     */
    public Double getGstPrice() {
        return GstPrice;
    }

    /**
     * @param GstPrice The GstPrice
     */
    public void setGstPrice(Double GstPrice) {
        this.GstPrice = GstPrice;
    }

    /**
     * @return The TotalPriceIncGstPrice
     */
    public Double getTotalPriceIncGstPrice() {
        return TotalPriceIncGstPrice;
    }

    /**
     * @param TotalPriceIncGstPrice The TotalPriceIncGstPrice
     */
    public void setTotalPriceIncGstPrice(Double TotalPriceIncGstPrice) {
        this.TotalPriceIncGstPrice = TotalPriceIncGstPrice;
    }

    /**
     * @return The Currency
     */
    public String getCurrency() {
        return Currency;
    }

    /**
     * @param Currency The Currency
     */
    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }


    public Cart() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(Products);
        dest.writeString(this.PromotionalCode);
        dest.writeValue(this.PromotionalValue);
        dest.writeValue(this.TotalPrice);
        dest.writeString(this.ShippingType);
        dest.writeValue(this.ShippingFee);
        dest.writeValue(this.GstText);
        dest.writeValue(this.GstPrice);
        dest.writeValue(this.TotalPriceIncGstPrice);
        dest.writeString(this.Currency);
        dest.writeValue(this.Count);
    }

    protected Cart(Parcel in) {
        this.Products = in.createTypedArrayList(Product.CREATOR);
        this.PromotionalCode = in.readString();
        this.PromotionalValue = (Double) in.readValue(Double.class.getClassLoader());
        this.TotalPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.ShippingType = in.readString();
        this.ShippingFee = (Double) in.readValue(Double.class.getClassLoader());
        this.GstText = (Double) in.readValue(Double.class.getClassLoader());
        this.GstPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.TotalPriceIncGstPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.Currency = in.readString();
        this.Count = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel source) {
            return new Cart(source);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };
}
