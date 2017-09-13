package com.applab.goodmorning.Login.model;

/**
 * -- =============================================
 * -- Author     : Edwin Cheong
 * -- Create date: 25/3/2016
 * -- Description: CRUDHelper .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.applab.goodmorning.Utilities.DBHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token implements Parcelable {

    @SerializedName("Token")
    @Expose
    private String Token;
    @SerializedName("UserId")
    @Expose
    private String UserId;
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
    @SerializedName("StateItem")
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
    @SerializedName("ExpiryDate")
    @Expose
    private String ExpiryDate;

    /**
     * @return The Token
     */
    public String getToken() {
        return Token;
    }

    /**
     * @param Token The Token
     */
    public void setToken(String Token) {
        this.Token = Token;
    }

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

    /**
     * @return The ExpiryDate
     */
    public String getExpiryDate() {
        return ExpiryDate;
    }

    /**
     * @param ExpiryDate The ExpiryDate
     */
    public void setExpiryDate(String ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    public Token() {

    }

    public Token(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.Token = in.readString();
        this.FirstName = in.readString();
        this.Email = in.readString();
        this.City = in.readString();
        this.Address = in.readString();
        this.Company = in.readString();
        this.ContactNo = in.readString();
        this.CountryId = in.readInt();
        this.Country = in.readString();
        this.LastName = in.readString();
        this.PasswordLastChangeDate = in.readString();
        this.LastUpdateDate = in.readString();
        this.Salutation = in.readString();
        this.Zipcode = in.readString();
        this.UserId = in.readString();
        this.StateCode = in.readString();
        this.State = in.readString();
        this.ExpiryDate = in.readString();
        this.DefAddress = in.readString();
        this.DefCity = in.readString();
        this.DefCountry = in.readString();
        this.DefState = in.readString();
        this.DefZipcode = in.readString();
        this.DefStateCode = in.readString();
        this.DefCountryId = in.readInt();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Token);
        dest.writeString(this.UserId);
        dest.writeString(this.Salutation);
        dest.writeString(this.FirstName);
        dest.writeString(this.LastName);
        dest.writeString(this.Email);
        dest.writeString(this.Company);
        dest.writeString(this.ContactNo);
        dest.writeInt(this.CountryId);
        dest.writeString(this.Country);
        dest.writeString(this.DefAddress);
        dest.writeString(this.DefZipcode);
        dest.writeString(this.DefCity);
        dest.writeString(this.DefState);
        dest.writeString(this.DefCountry);
        dest.writeInt(this.DefCountryId);
        dest.writeString(this.DefStateCode);
        dest.writeString(this.Address);
        dest.writeString(this.City);
        dest.writeString(this.StateCode);
        dest.writeString(this.State);
        dest.writeString(this.Zipcode);
        dest.writeString(this.PasswordLastChangeDate);
        dest.writeString(this.LastUpdateDate);
        dest.writeString(this.ExpiryDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static final Creator CREATOR = new Creator() {
        @Override
        public Token createFromParcel(Parcel in) {
            return new Token(in);
        }

        @Override
        public Token[] newArray(int size) {
            return new Token[size];
        }
    };

    public static Token getToken(Cursor cursor, int position) {
        com.applab.goodmorning.Login.model.Token token = new Token();
        cursor.moveToPosition(position);
        token.setToken(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_ACCESS_TOKEN)));
        token.setUserId(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_USER_ID)));
        token.setSalutation(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_SALUTATION)));
        token.setFirstName(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_FIRST_NAME)));
        token.setLastName(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_LAST_NAME)));
        token.setEmail(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_EMAIL)));
        token.setCompany(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_COMPANY)));
        token.setContactNo(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_CONTACT_NO)));
        token.setCountryId(cursor.getInt(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_COUNTRY_ID)));
        token.setCountry(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_COUNTRY)));
        token.setDefAddress(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_DEF_ADDRESS)));
        token.setDefZipcode(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_DEF_ZIPCODE)));
        token.setDefCity(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_DEF_CITY)));
        token.setDefState(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_DEF_STATE)));
        token.setDefCountry(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_DEF_COUNTRY)));
        token.setDefCountryId(cursor.getInt(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_DEF_COUNTRY_ID)));
        token.setDefStateCode(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_DEF_STATE_CODE)));
        token.setAddress(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_ADDRESS)));
        token.setCity(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_CITY)));
        token.setState(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_STATE)));
        token.setStateCode(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_STATE_CODE)));
        token.setZipcode(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_ZIP_CODE)));
        token.setPasswordLastChangeDate(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_PASSWORD_LAST_CHANGE_DATE)));
        token.setLastUpdateDate(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_LAST_UPDATE_DATE)));
        token.setExpiryDate(cursor.getString(cursor.getColumnIndex(DBHelper.TOKEN_COLUMN_EXPIRED_DATE)));
        return token;
    }
}
