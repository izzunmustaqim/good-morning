package com.applab.goodmorning.Checkout.model;

/**
 * Created by user on 15/4/2016.
 */
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartItem implements Parcelable {

    private List<Cart> items = new ArrayList<Cart>();
    /**
     *
     * @return
     * The items
     */
    public List<Cart> getCarts() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setCarts(List<Cart> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(items);
    }

    public CartItem() {
    }

    protected CartItem(Parcel in) {
        this.items = in.createTypedArrayList(Cart.CREATOR);
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel source) {
            return new CartItem(source);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };
}