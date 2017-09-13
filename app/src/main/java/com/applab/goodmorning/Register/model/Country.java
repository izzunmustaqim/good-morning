package com.applab.goodmorning.Register.model;

/**
 * Created by user on 29/3/2016.
 */

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.applab.goodmorning.Utilities.DBHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 29/3/2016
 * -- Description: Country.java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class Country implements Parcelable {

    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("CountryCode")
    @Expose
    private String CountryCode;
    @SerializedName("CountryName")
    @Expose
    private String CountryName;
    @SerializedName("Currency")
    @Expose
    private String Currency;

    /**
     * @return The Id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * @param Id The Id
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     * @return The CountryCode
     */
    public String getCountryCode() {
        return CountryCode;
    }

    /**
     * @param CountryCode The CountryCode
     */
    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    /**
     * @return The CountryName
     */
    public String getCountryName() {
        return CountryName;
    }

    /**
     * @param CountryName The CountryName
     */
    public void setCountryName(String CountryName) {
        this.CountryName = CountryName;
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

    public Country() {

    }

    public Country(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.Id = in.readInt();
        this.CountryCode = in.readString();
        this.CountryName = in.readString();
        this.Currency = in.readString();

    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.CountryCode);
        dest.writeString(this.CountryName);
        dest.writeString(this.Currency);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public static Country getCountry(Cursor cursor, int position) {
        Country country = new Country();
        cursor.moveToPosition(position);
        country.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COUNTRY_COLUMN_COUNTRY_ID)));
        country.setCountryCode(cursor.getString(cursor.getColumnIndex(DBHelper.COUNTRY_COLUMN_CODE)));
        country.setCountryName(cursor.getString(cursor.getColumnIndex(DBHelper.COUNTRY_COLUMN_NAME)));
        country.setCurrency(cursor.getString(cursor.getColumnIndex(DBHelper.COUNTRY_COLUMN_CURRENCY)));
        return country;
    }

}
