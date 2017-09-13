package com.applab.goodmorning.Event.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 4/3/2016.
 */
public class Schedule implements Parcelable {
    @SerializedName("EventId")
    @Expose
    private Integer EventId;
    @SerializedName("EventTitle")
    @Expose
    private String EventTitle;
    @SerializedName("EventFrom")
    @Expose
    private String EventFrom;
    @SerializedName("EventTo")
    @Expose
    private String EventTo;
    @SerializedName("Location")
    @Expose
    private String Location;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("Remarks")
    @Expose
    private String Remarks;
    @SerializedName("Image")
    @Expose
    private String Image;
    @SerializedName("CreateBy")
    @Expose
    private String CreateBy;
    @SerializedName("ShowDate")
    @Expose
    private String ShowDate;

    public String getShowDate() {
        return ShowDate;
    }

    public void setShowDate(String showDate) {
        ShowDate = showDate;
    }

    /**
     * @return The EventId
     */
    public Integer getEventId() {
        return EventId;
    }

    /**
     * @param EventId The EventId
     */
    public void setEventId(Integer EventId) {
        this.EventId = EventId;
    }

    /**
     * @return The EventTitle
     */
    public String getEventTitle() {
        return EventTitle;
    }

    /**
     * @param EventTitle The EventTitle
     */
    public void setEventTitle(String EventTitle) {
        this.EventTitle = EventTitle;
    }

    /**
     * @return The EventFrom
     */
    public String getEventFrom() {
        return EventFrom;
    }

    /**
     * @param EventFrom The EventFrom
     */
    public void setEventFrom(String EventFrom) {
        this.EventFrom = EventFrom;
    }

    /**
     * @return The EventTo
     */
    public String getEventTo() {
        return EventTo;
    }

    /**
     * @param EventTo The EventTo
     */
    public void setEventTo(String EventTo) {
        this.EventTo = EventTo;
    }

    /**
     * @return The Location
     */
    public String getLocation() {
        return Location;
    }

    /**
     * @param Location The Location
     */
    public void setLocation(String Location) {
        this.Location = Location;
    }

    /**
     * @return The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return The Remarks
     */
    public String getRemarks() {
        return Remarks;
    }

    /**
     * @param Remarks The Remarks
     */
    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    /**
     * @return The Image
     */
    public String getImage() {
        return Image;
    }

    /**
     * @param Image The Image
     */
    public void setImage(String Image) {
        this.Image = Image;
    }

    /**
     * @return The CreateBy
     */
    public String getCreateBy() {
        return CreateBy;
    }

    /**
     * @param CreateBy The CreateBy
     */
    public void setCreateBy(String CreateBy) {
        this.CreateBy = CreateBy;
    }

    public Schedule() {

    }

    public Schedule(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.EventId = in.readInt();
        this.EventTitle = in.readString();
        this.EventFrom = in.readString();
        this.EventTo = in.readString();
        this.Location = in.readString();
        this.Description = in.readString();
        this.Remarks = in.readString();
        this.Image = in.readString();
        this.CreateBy = in.readString();
        this.ShowDate = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.EventId);
        dest.writeString(this.EventTitle);
        dest.writeString(this.EventFrom);
        dest.writeString(this.EventTo);
        dest.writeString(this.Location);
        dest.writeString(this.Description);
        dest.writeString(this.Remarks);
        dest.writeString(this.Image);
        dest.writeString(this.CreateBy);
        dest.writeString(this.ShowDate);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Schedule createFromParcel(Parcel in) {
            return new Schedule(in);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
