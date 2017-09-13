package com.applab.goodmorning.Promotions.model;

/**
 * Created by User on 4/13/2016.
 */
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 11/4/2016
 * -- Description: PromotionItem .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */


public class PromotionItem implements Parcelable {


    @SerializedName("items")
    @Expose
    private List<Promotion> items = new ArrayList<Promotion>();


    /**
     *
     * @return
     * The items
     */
    public List<Promotion> getItems() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setItems(List<Promotion> items) {
        this.items = items;
    }

    public PromotionItem() {
    }

    public PromotionItem(Parcel in) {
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
        public Promotion createFromParcel(Parcel in) {
            return new Promotion(in);
        }

        @Override
        public Promotion[] newArray(int size) {
            return new Promotion[size];
        }
    };
}
