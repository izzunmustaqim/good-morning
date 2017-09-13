package com.applab.goodmorning.State.model;

/**
 * Created by user on 31-Mar-16.
 */

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.applab.goodmorning.Utilities.DBHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class State implements Parcelable {
    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("StateCode")
    @Expose
    private String StateCode;
    @SerializedName("StateName")
    @Expose
    private String StateName;
    private int CountryId;

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
     * @return The StateCode
     */
    public String getStateCode() {
        return StateCode;
    }

    /**
     * @param StateCode The StateCode
     */
    public void setStateCode(String StateCode) {
        this.StateCode = StateCode;
    }

    /**
     * @return The StateName
     */
    public String getStateName() {
        return StateName;
    }

    /**
     * @param StateName The StateName
     */
    public void setStateName(String StateName) {
        this.StateName = StateName;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public State() {

    }

    public State(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.Id = in.readInt();
        this.CountryId = in.readInt();
        this.StateCode = in.readString();
        this.StateName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeInt(this.CountryId);
        dest.writeString(this.StateCode);
        dest.writeString(this.StateName);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public State createFromParcel(Parcel in) {
            return new State(in);
        }

        @Override
        public State[] newArray(int size) {
            return new State[size];
        }
    };

    public static State getStateItem(Cursor cursor, int position) {
        State state = new State();
        cursor.moveToPosition(position);
        state.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.STATE_COLUMN_STATE_ID)));
        state.setCountryId(cursor.getInt(cursor.getColumnIndex(DBHelper. STATE_COLUMN_COUNTRY_ID )));
        state.setStateCode(cursor.getString(cursor.getColumnIndex(DBHelper.STATE_COLUMN_CODE)));
        state.setStateName(cursor.getString(cursor.getColumnIndex(DBHelper.STATE_COLUMN_NAME)));
        return state;
    }
}
