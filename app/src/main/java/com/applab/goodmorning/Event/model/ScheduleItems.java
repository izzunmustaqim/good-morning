package com.applab.goodmorning.Event.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by user on 1/4/2016.
 */
public class ScheduleItems implements Parcelable {
    @SerializedName("items")
    @Expose
    private ArrayList<Schedule> items = new ArrayList<Schedule>();

    /**
     * @return The items
     */
    public ArrayList<Schedule> getSchedules() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setSchedules(ArrayList<Schedule> items) {
        this.items = items;
    }

    public void readFromParcel(Parcel in) {
        this.items = in.readArrayList(Schedule.class.getClassLoader());
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(items);
    }

    public ScheduleItems() {

    }

    public ScheduleItems(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public ScheduleItems createFromParcel(Parcel in) {
            return new ScheduleItems(in);
        }

        @Override
        public ScheduleItems[] newArray(int size) {
            return new ScheduleItems[size];
        }
    };
}
