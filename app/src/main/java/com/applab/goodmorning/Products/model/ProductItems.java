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


public class ProductItems implements Parcelable {
    @SerializedName("items")
    @Expose
    private ArrayList<Product> items = new ArrayList<Product>();

    /**
     * @return The items
     */
    public ArrayList<Product> getItems() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setItems(ArrayList<Product> items) {
        this.items = items;
    }

    public ProductItems() {

    }

    public ProductItems(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.items = in.readArrayList(Product.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.items);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public ProductItems createFromParcel(Parcel in) {
            return new ProductItems(in);
        }

        @Override
        public ProductItems[] newArray(int size) {
            return new ProductItems[size];
        }
    };
}
