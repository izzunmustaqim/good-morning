package com.applab.goodmorning.Welcome.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 04-Mar-16.
 */
public class Products implements Parcelable {
    private String imgProducts;
    private String productsPrice;
    private String productsName;
    private String productsWeight;
    private int productsAmount;
    private String productsPromotion;

    public Products() {

    }

    public Products(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.imgProducts = in.readString();
        this.productsPrice = in.readString();
        this.productsName = in.readString();
        this.productsWeight = in.readString();
        this.productsAmount = in.readInt();
        this.productsPromotion = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imgProducts);
        dest.writeString(this.productsPrice);
        dest.writeString(this.productsName);
        dest.writeString(this.productsWeight);
        dest.writeInt(this.productsAmount);
        dest.writeString(this.productsPromotion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };

    public int getProductsAmount() {
        return productsAmount;
    }

    public void setProductsAmount(int productsAmount) {
        this.productsAmount = productsAmount;
    }

    public String getImgProducts() {
        return imgProducts;
    }

    public void setImgProducts(String imgProducts) {
        this.imgProducts = imgProducts;
    }

    public String getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(String productsPrice) {
        this.productsPrice = productsPrice;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public String getProductsWeight() {
        return productsWeight;
    }

    public void setProductsWeight(String productsWeight) {
        this.productsWeight = productsWeight;
    }

    public String getProductsPromotion() {
        return productsPromotion;
    }

    public void setProductsPromotion(String productsPromotion) {
        this.productsPromotion = productsPromotion;
    }
}
