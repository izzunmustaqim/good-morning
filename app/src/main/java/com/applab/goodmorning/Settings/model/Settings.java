package com.applab.goodmorning.Settings.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 09-Mar-16.
 */
public class Settings implements Parcelable {
    private String selection;
    private String itemSelected;

    public Settings() {

    }

    public Settings(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.selection = in.readString();
        this.itemSelected = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.selection);
        dest.writeString(this.itemSelected);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Settings createFromParcel(Parcel in) {
            return new Settings(in);
        }

        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(String itemSelected) {
        this.itemSelected = itemSelected;
    }
}
