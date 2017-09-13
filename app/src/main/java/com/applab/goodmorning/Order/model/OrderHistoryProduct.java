package com.applab.goodmorning.Order.model;

/**
 * Created by User on 8/4/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderHistoryProduct implements Parcelable {

    @SerializedName("OrderId")
    @Expose
    private String OrderId;
    @SerializedName("ProductId")
    @Expose
    private Integer ProductId;
    @SerializedName("ProductName")
    @Expose
    private String ProductName;
    @SerializedName("ProductSubtitle")
    @Expose
    private String ProductSubtitle;
    @SerializedName("ProductImage")
    @Expose
    private String ProductImage;
    @SerializedName("Quantity")
    @Expose
    private Integer Quantity;
    @SerializedName("Price")
    @Expose
    private Double Price;

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
     * @return The ProductId
     */
    public Integer getProductId() {
        return ProductId;
    }

    /**
     * @param ProductId The ProductId
     */
    public void setProductId(Integer ProductId) {
        this.ProductId = ProductId;
    }

    /**
     * @return The ProductName
     */
    public String getProductName() {
        return ProductName;
    }

    /**
     * @param ProductName The ProductName
     */
    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    /**
     * @return The ProductSubtitle
     */
    public String getProductSubtitle() {
        return ProductSubtitle;
    }

    /**
     * @param ProductSubtitle The ProductSubtitle
     */
    public void setProductSubtitle(String ProductSubtitle) {
        this.ProductSubtitle = ProductSubtitle;
    }

    /**
     * @return The ProductImage
     */
    public String getProductImage() {
        return ProductImage;
    }

    /**
     * @param ProductImage The ProductImage
     */
    public void setProductImage(String ProductImage) {
        this.ProductImage = ProductImage;
    }

    /**
     * @return The Quantity
     */
    public Integer getQuantity() {
        return Quantity;
    }

    /**
     * @param Quantity The Quantity
     */
    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    /**
     * @return The Price
     */
    public Double getPrice() {
        return Price;
    }

    /**
     * @param Price The Price
     */
    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public OrderHistoryProduct() {

    }

    public OrderHistoryProduct(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.OrderId = in.readString();
        this.ProductId = in.readInt();
        this.ProductName = in.readString();
        this.ProductSubtitle = in.readString();
        this.ProductImage = in.readString();
        this.Quantity = in.readInt();
        this.Price = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.OrderId);
        dest.writeInt(this.ProductId);
        dest.writeString(this.ProductName);
        dest.writeString(this.ProductSubtitle);
        dest.writeString(this.ProductImage);
        dest.writeInt(this.Quantity);
        dest.writeDouble(this.Price);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public OrderHistoryProduct createFromParcel(Parcel in) {
            return new OrderHistoryProduct(in);
        }

        @Override
        public OrderHistoryProduct[] newArray(int size) {
            return new OrderHistoryProduct[size];
        }
    };
}