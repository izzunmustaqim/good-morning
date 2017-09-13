package com.applab.goodmorning.Register.model;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 29/3/2016
 * -- Description: Register.java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register  {

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
    @SerializedName("Company")
    @Expose
    private String Company;
    @SerializedName("ContactNo")
    @Expose
    private String ContactNo;
    @SerializedName("Address")
    @Expose
    private String Address;
    @SerializedName("City")
    @Expose
    private String City;
    @SerializedName("Zipcode")
    @Expose
    private String Zipcode;
    @SerializedName("StateCode")
    @Expose
    private String StateCode;
    @SerializedName("CountryId")
    @Expose
    private Integer CountryId;
    @SerializedName("DefAddress")
    @Expose
    private String DefAddress;
    @SerializedName("DefCity")
    @Expose
    private String DefCity;
    @SerializedName("DefZipcode")
    @Expose
    private String DefZipcode;
    @SerializedName("DefStateCode")
    @Expose
    private String DefStateCode;
    @SerializedName("DefCountryId")
    @Expose
    private Integer DefCountryId;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("Password")
    @Expose
    private String Password;
    @SerializedName("ConfirmPassword")
    @Expose
    private String ConfirmPassword;

    /**
     *
     * @return
     * The Username
     */
    public String getUsername() {
        return Username;
    }

    /**
     *
     * @param Username
     * The Username
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }

    /**
     *
     * @return
     * The Salutation
     */
    public String getSalutation() {
        return Salutation;
    }

    /**
     *
     * @param Salutation
     * The Salutation
     */
    public void setSalutation(String Salutation) {
        this.Salutation = Salutation;
    }

    /**
     *
     * @return
     * The FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     *
     * @param FirstName
     * The FirstName
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     *
     * @return
     * The LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     *
     * @param LastName
     * The LastName
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     *
     * @return
     * The Company
     */
    public String getCompany() {
        return Company;
    }

    /**
     *
     * @param Company
     * The Company
     */
    public void setCompany(String Company) {
        this.Company = Company;
    }

    /**
     *
     * @return
     * The ContactNo
     */
    public String getContactNo() {
        return ContactNo;
    }

    /**
     *
     * @param ContactNo
     * The ContactNo
     */
    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
    }

    /**
     *
     * @return
     * The Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     *
     * @param Address
     * The Address
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     *
     * @return
     * The City
     */
    public String getCity() {
        return City;
    }

    /**
     *
     * @param City
     * The City
     */
    public void setCity(String City) {
        this.City = City;
    }

    /**
     *
     * @return
     * The Zipcode
     */
    public String getZipcode() {
        return Zipcode;
    }

    /**
     *
     * @param Zipcode
     * The Zipcode
     */
    public void setZipcode(String Zipcode) {
        this.Zipcode = Zipcode;
    }

    /**
     *
     * @return
     * The StateCode
     */
    public String getStateCode() {
        return StateCode;
    }

    /**
     *
     * @param StateCode
     * The StateCode
     */
    public void setStateCode(String StateCode) {
        this.StateCode = StateCode;
    }

    /**
     *
     * @return
     * The CountryId
     */
    public Integer getCountryId() {
        return CountryId;
    }

    /**
     *
     * @param CountryId
     * The CountryId
     */
    public void setCountryId(Integer CountryId) {
        this.CountryId = CountryId;
    }

    /**
     *
     * @return
     * The DefAddress
     */
    public String getDefAddress() {
        return DefAddress;
    }

    /**
     *
     * @param DefAddress
     * The DefAddress
     */
    public void setDefAddress(String DefAddress) {
        this.DefAddress = DefAddress;
    }

    /**
     *
     * @return
     * The DefCity
     */
    public String getDefCity() {
        return DefCity;
    }

    /**
     *
     * @param DefCity
     * The DefCity
     */
    public void setDefCity(String DefCity) {
        this.DefCity = DefCity;
    }

    /**
     *
     * @return
     * The DefZipcode
     */
    public String getDefZipcode() {
        return DefZipcode;
    }

    /**
     *
     * @param DefZipcode
     * The DefZipcode
     */
    public void setDefZipcode(String DefZipcode) {
        this.DefZipcode = DefZipcode;
    }

    /**
     *
     * @return
     * The DefStateCode
     */
    public String getDefStateCode() {
        return DefStateCode;
    }

    /**
     *
     * @param DefStateCode
     * The DefStateCode
     */
    public void setDefStateCode(String DefStateCode) {
        this.DefStateCode = DefStateCode;
    }

    /**
     *
     * @return
     * The DefCountryId
     */
    public Integer getDefCountryId() {
        return DefCountryId;
    }

    /**
     *
     * @param DefCountryId
     * The DefCountryId
     */
    public void setDefCountryId(Integer DefCountryId) {
        this.DefCountryId = DefCountryId;
    }

    /**
     *
     * @return
     * The Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     *
     * @param Email
     * The Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     *
     * @return
     * The Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     *
     * @param Password
     * The Password
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     *
     * @return
     * The ConfirmPassword
     */
    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    /**
     *
     * @param ConfirmPassword
     * The ConfirmPassword
     */
    public void setConfirmPassword(String ConfirmPassword) {
        this.ConfirmPassword = ConfirmPassword;
    }

}


