package com.applab.goodmorning.Order.model;

/**
 * Created by User on 8/4/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderItem implements Parcelable {

    @SerializedName("items")
    @Expose
    private List<Order> items = new ArrayList<Order>();

    /**
     * @return The items
     */
    public List<Order> getItems() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setItems(List<Order> items) {
        this.items = items;
    }

    public OrderItem() {

    }

    public OrderItem(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.items = in.readArrayList(items.getClass().getClassLoader());
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
        public OrderItem createFromParcel(Parcel in) {
            return new OrderItem(in);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };
}