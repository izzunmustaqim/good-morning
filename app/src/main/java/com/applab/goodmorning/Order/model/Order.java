package com.applab.goodmorning.Order.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 08-Mar-16.
 */
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order implements Parcelable {

    @SerializedName("OrderId")
    @Expose
    private String OrderId;
    @SerializedName("CreateDate")
    @Expose
    private String CreateDate;
    @SerializedName("Status")
    @Expose
    private String Status;
    @SerializedName("PromotionalCode")
    @Expose
    private String PromotionalCode;
    @SerializedName("PromotionalValue")
    @Expose
    private Double PromotionalValue;
    @SerializedName("TotalPrice")
    @Expose
    private Double TotalPrice;
    @SerializedName("Gst")
    @Expose
    private Double Gst;
    @SerializedName("GstPrice")
    @Expose
    private Double GstPrice;
    @SerializedName("ShippingPrice")
    @Expose
    private Double ShippingPrice;
    @SerializedName("TotalPriceIncGst")
    @Expose
    private Double TotalPriceIncGst;
    @SerializedName("Currency")
    @Expose
    private String Currency;
    @SerializedName("OrderHistoryProducts")
    @Expose
    private List<OrderHistoryProduct> OrderHistoryProducts = new ArrayList<OrderHistoryProduct>();

    /**
     * @return The OrderId
     */
    public String getOrderId() {
        return OrderId;
    }

    /**
     * @param OrderId The OrderId
     */
    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
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
     * @return The Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status The Status
     */
    public void setStatus(String Status) {
        this.Status = Status;
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
     * @return The Gst
     */
    public Double getGst() {
        return Gst;
    }

    /**
     * @param Gst The Gst
     */
    public void setGst(Double Gst) {
        this.Gst = Gst;
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
     * @return The ShippingPrice
     */
    public Double getShippingPrice() {
        return ShippingPrice;
    }

    /**
     * @param ShippingPrice The ShippingPrice
     */
    public void setShippingPrice(Double ShippingPrice) {
        this.ShippingPrice = ShippingPrice;
    }

    /**
     * @return The TotalPriceIncGst
     */
    public Double getTotalPriceIncGst() {
        return TotalPriceIncGst;
    }

    /**
     * @param TotalPriceIncGst The TotalPriceIncGst
     */
    public void setTotalPriceIncGst(Double TotalPriceIncGst) {
        this.TotalPriceIncGst = TotalPriceIncGst;
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

    /**
     * @return The OrderHistoryProducts
     */
    public List<OrderHistoryProduct> getOrderHistoryProducts() {
        return OrderHistoryProducts;
    }

    /**
     * @param OrderHistoryProducts The OrderHistoryProducts
     */
    public void setOrderHistoryProducts(List<OrderHistoryProduct> OrderHistoryProducts) {
        this.OrderHistoryProducts = OrderHistoryProducts;
    }

    public Order() {

    }

    public Order(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.OrderId = in.readString();
        this.CreateDate = in.readString();
        this.Status = in.readString();
        this.PromotionalCode = in.readString();
        this.PromotionalValue = in.readDouble();
        this.TotalPrice = in.readDouble();
        this.Gst = in.readDouble();
        this.GstPrice = in.readDouble();
        this.ShippingPrice = in.readDouble();
        this.TotalPriceIncGst = in.readDouble();
        this.Currency = in.readString();
        this.OrderHistoryProducts = in.readArrayList(OrderHistoryProduct.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.OrderId);
        dest.writeString(this.CreateDate);
        dest.writeString(this.Status);
        dest.writeString(this.PromotionalCode);
        dest.writeDouble(this.PromotionalValue);
        dest.writeDouble(this.TotalPrice);
        dest.writeDouble(this.Gst);
        dest.writeDouble(this.GstPrice);
        dest.writeDouble(this.ShippingPrice);
        dest.writeDouble(this.TotalPriceIncGst);
        dest.writeString(this.Currency);
        dest.writeList(this.OrderHistoryProducts);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}