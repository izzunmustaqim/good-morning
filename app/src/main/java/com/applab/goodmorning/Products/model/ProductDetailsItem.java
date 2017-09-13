package com.applab.goodmorning.Products.model;

/**
 * Created by user on 29-Mar-16.
 */

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsItem  {

    @SerializedName("items")
    @Expose
    private List<ProductDetails> items = new ArrayList<ProductDetails>();

    /**
     * @return The items
     */
    public List<ProductDetails> getItems() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setItems(List<ProductDetails> items) {
        this.items = items;
    }

}