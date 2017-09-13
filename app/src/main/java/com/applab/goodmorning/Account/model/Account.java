package com.applab.goodmorning.Account.model;

/**
 * Created by user on 28-Mar-16.
 */

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.applab.goodmorning.Utilities.DBHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account implements Parcelable {
    @SerializedName("UserId")
    @Expose
    private String UserId;
    @SerializedName("Username")
    @Expose
    private String Username;
    @SerializedName("Salutation")
    @Expose
    private String Salutation;
    @SerializedName("FirstName")
    @Expose
    private String FirstName;
    @SerializedName("LastName")
    @Expose
    private String LastName;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("Company")
    @Expose
    private String Company;
    @SerializedName("ContactNo")
    @Expose
    private String ContactNo;
    @SerializedName("Address")
    @Expose
    private String Address;
    @SerializedName("Zipcode")
    @Expose
    private String Zipcode;
    @SerializedName("City")
    @Expose
    private String City;
    @SerializedName("StateCode")
    @Expose
    private String StateCode;
    @SerializedName("State")
    @Expose
    private String State;
    @SerializedName("CountryId")
    @Expose
    private Integer CountryId;
    @SerializedName("Country")
    @Expose
    private String Country;
    @SerializedName("DefAddress")
    @Expose
    private String DefAddress;
    @SerializedName("DefZipcode")
    @Expose
    private String DefZipcode;
    @SerializedName("DefCity")
    @Expose
    private String DefCity;
    @SerializedName("DefState")
    @Expose
    private String DefState;
    @SerializedName("DefCountry")
    @Expose
    private String DefCountry;
    @SerializedName("DefCountryId")
    @Expose
    private Integer DefCountryId;
    @SerializedName("DefStateCode")
    @Expose
    private String DefStateCode;
    @SerializedName("PasswordLastChangeDate")
    @Expose
    private String PasswordLastChangeDate;
    @SerializedName("LastUpdateDate")
    @Expose
    private String LastUpdateDate;

    /**
     * @return The UserId
     */
    public String getUserId() {
        return UserId;
    }

    /**
     * @param UserId The UserId
     */
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    /**
     * @return The Username
     */
    public String getUsername() {
        return Username;
    }

    /**
     * @param Username The Username
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }

    /**
     * @return The Salutation
     */
    public String getSalutation() {
        return Salutation;
    }

    /**
     * @param Salutation The Salutation
     */
    public void setSalutation(String Salutation) {
        this.Salutation = Salutation;
    }

    /**
     * @return The FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * @param FirstName The FirstName
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * @return The LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * @param LastName The LastName
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * @return The Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email The Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return The Company
     */
    public String getCompany() {
        return Company;
    }

    /**
     * @param Company The Company
     */
    public void setCompany(String Company) {
        this.Company = Company;
    }

    /**
     * @return The ContactNo
     */
    public String getContactNo() {
        return ContactNo;
    }

    /**
     * @param ContactNo The ContactNo
     */
    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
    }

    /**
     * @return The Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address The Address
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return The Zipcode
     */
    public String getZipcode() {
        return Zipcode;
    }

    /**
     * @param Zipcode The Zipcode
     */
    public void setZipcode(String Zipcode) {
        this.Zipcode = Zipcode;
    }

    /**
     * @return The City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param City The City
     */
    public void setCity(String City) {
        this.City = City;
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
     * @return The StateItem
     */
    public String getState() {
        return State;
    }

    /**
     * @param State The StateItem
     */
    public void setState(String State) {
        this.State = State;
    }

    /**
     * @return The Country
     */
    public String getCountry() {
        return Country;
    }

    /**
     * @param Country The Country
     */
    public void setCountry(String Country) {
        this.Country = Country;
    }

    /**
     * @return The CountryId
     */
    public Integer getCountryId() {
        return CountryId;
    }

    /**
     * @param CountryId The CountryId
     */
    public void setCountryId(Integer CountryId) {
        this.CountryId = CountryId;
    }

    /**
     * @return The DefAddress
     */
    public String getDefAddress() {
        return DefAddress;
    }

    /**
     * @param DefAddress The DefAddress
     */
    public void setDefAddress(String DefAddress) {
        this.DefAddress = DefAddress;
    }

    /**
     * @return The DefZipcode
     */
    public String getDefZipcode() {
        return DefZipcode;
    }

    /**
     * @param DefZipcode The DefZipcode
     */
    public void setDefZipcode(String DefZipcode) {
        this.DefZipcode = DefZipcode;
    }

    /**
     * @return The DefCity
     */
    public String getDefCity() {
        return DefCity;
    }

    /**
     * @param DefCity The DefCity
     */
    public void setDefCity(String DefCity) {
        this.DefCity = DefCity;
    }

    /**
     * @return The DefState
     */
    public String getDefState() {
        return DefState;
    }

    /**
     * @param DefState The DefState
     */
    public void setDefState(String DefState) {
        this.DefState = DefState;
    }

    /**
     * @return The DefCountry
     */
    public String getDefCountry() {
        return DefCountry;
    }

    /**
     * @param DefCountry The DefCountry
     */
    public void setDefCountry(String DefCountry) {
        this.DefCountry = DefCountry;
    }

    /**
     * @return The DefStateCode
     */
    public String getDefStateCode() {
        return DefStateCode;
    }

    /**
     * @param DefStateCode The DefStateCode
     */
    public void setDefStateCode(String DefStateCode) {
        this.DefStateCode = DefStateCode;
    }

    /**
     * @return The DefCountryId
     */
    public Integer getDefCountryId() {
        return DefCountryId;
    }

    /**
     * @param DefCountryId The DefCountryId
     */
    public void setDefCountryId(Integer DefCountryId) {
        this.DefCountryId = DefCountryId;
    }

    /**
     * @return The PasswordLastChangeDate
     */
    public String getPasswordLastChangeDate() {
        return PasswordLastChangeDate;
    }

    /**
     * @param PasswordLastChangeDate The PasswordLastChangeDate
     */
    public void setPasswordLastChangeDate(String PasswordLastChangeDate) {
        this.PasswordLastChangeDate = PasswordLastChangeDate;
    }

    /**
     * @return The LastUpdateDate
     */
    public String getLastUpdateDate() {
        return LastUpdateDate;
    }

    /**
     * @param LastUpdateDate The LastUpdateDate
     */
    public void setLastUpdateDate(String LastUpdateDate) {
        this.LastUpdateDate = LastUpdateDate;
    }

    public Account() {

    }

    public Account(Parcel in) {
        readFromParcel(in);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public void readFromParcel(Parcel in) {
        this.UserId = in.readString();
        this.Username = in.readString();
        this.Salutation = in.readString();
        this.FirstName = in.readString();
        this.LastName = in.readString();
        this.Email = in.readString();
        this.Company = in.readString();
        this.ContactNo = in.readString();
        this.Address = in.readString();
        this.Zipcode = in.readString();
        this.City = in.readString();
        this.CountryId = in.readInt();
        this.StateCode = in.readString();
        this.State = in.readString();
        this.Country = in.readString();
        this.DefAddress = in.readString();
        this.DefZipcode = in.readString();
        this.DefCity = in.readString();
        this.DefState = in.readString();
        this.DefCountry = in.readString();
        this.DefCountryId = in.readInt();
        this.DefStateCode = in.readString();
        this.PasswordLastChangeDate = in.readString();
        this.LastUpdateDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.UserId);
        dest.writeString(this.Username);
        dest.writeString(this.Salutation);
        dest.writeString(this.FirstName);
        dest.writeString(this.LastName);
        dest.writeString(this.Email);
        dest.writeString(this.Company);
        dest.writeString(this.ContactNo);
        dest.writeString(this.Address);
        dest.writeString(this.Zipcode);
        dest.writeString(this.City);
        dest.writeInt(this.CountryId);
        dest.writeString(this.StateCode);
        dest.writeString(this.State);
        dest.writeString(this.Country);
        dest.writeString(this.DefAddress);
        dest.writeString(this.DefZipcode);
        dest.writeString(this.DefCity);
        dest.writeString(this.DefState);
        dest.writeString(this.DefCountry);
        dest.writeInt(this.DefCountryId);
        dest.writeString(this.DefStateCode);
        dest.writeString(this.PasswordLastChangeDate);
        dest.writeString(this.LastUpdateDate);
    }

    public static Account getAccount(Cursor cursor, int position) {
        cursor.moveToPosition(position);
        Account acccount = new Account();
        acccount.setUserId(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_USER_ID)));
        acccount.setUsername(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_USER_NAME)));
        acccount.setSalutation(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_SALUTATION)));
        acccount.setFirstName(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_FIRST_NAME)));
        acccount.setLastName(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_LAST_NAME)));
        acccount.setEmail(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_EMAIL)));
        acccount.setCompany(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_COMPANY)));
        acccount.setContactNo(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_CONTACT_NO)));
        acccount.setAddress(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_ADDRESS)));
        acccount.setZipcode(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_ZIPCODE)));
        acccount.setCity(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_CITY)));
        acccount.setStateCode(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_STATE_CODE)));
        acccount.setCountryId(cursor.getInt(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_COUNTRY_ID)));
        acccount.setState(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_STATE)));
        acccount.setStateCode(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_STATE_CODE)));
        acccount.setCountry(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_COUNTRY)));
        acccount.setCountryId(cursor.getInt(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_COUNTRY_ID)));
        acccount.setDefAddress(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_DEF_ADDRESS)));
        acccount.setDefCity(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_DEF_CITY)));
        acccount.setDefZipcode(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_DEF_ZIPCODE)));
        acccount.setDefState(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_DEF_STATE)));
        acccount.setDefCountry(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_DEF_COUNTRY)));
        acccount.setDefCountryId(cursor.getInt(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_DEF_COUNTRY_ID)));
        acccount.setDefStateCode(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_DEF_STATE_CODE)));
        acccount.setPasswordLastChangeDate(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_PASSWORD_LAST_CHAGE_DATE)));
        acccount.setLastUpdateDate(cursor.getString(cursor.getColumnIndex(DBHelper.ACCOUNT_COLUMN_LAST_UPDATE_DATE)));
        return acccount;
    }
}
