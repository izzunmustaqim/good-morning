package com.applab.goodmorning.ContactUs.model;

/**
 * Created by user on 8/4/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUsItem implements Parcelable {

    @SerializedName("items")
    @Expose
    private List<ContactUs> items = new ArrayList<ContactUs>();

    /**
     * @return The items
     */
    public List<ContactUs> getContactUss() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setContactUss(List<ContactUs> items) {
        this.items = items;
    }

    public void readFromParcel(Parcel in) {
        this.items = in.readArrayList(ContactUs.class.getClassLoader());
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(items);
    }

    public ContactUsItem() {

    }

    public ContactUsItem(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public ContactUsItem createFromParcel(Parcel in) {
            return new ContactUsItem(in);
        }

        @Override
        public ContactUsItem[] newArray(int size) {
            return new ContactUsItem[size];
        }
    };

}