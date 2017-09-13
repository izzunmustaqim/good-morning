package com.applab.goodmorning.Enquiry.model;

/**
 * Created by user on 12/4/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Enquiry {

    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("ContactNo")
    @Expose
    private String ContactNo;
    @SerializedName("Purpose")
    @Expose
    private String Purpose;
    @SerializedName("Message")
    @Expose
    private String Message;

    @SerializedName("FirstName")
    @Expose
    private String FirstName;
    @SerializedName("LastName")
    @Expose
    private String LastName;
    @SerializedName("CompanyName")
    @Expose
    private String CompanyName;
    @SerializedName("Address")
    @Expose
    private String Address;
    @SerializedName("Subject")
    @Expose
    private String Subject;

    @SerializedName("OutletName")
    @Expose
    private String OutletName;
    @SerializedName("OutletAddress")
    @Expose
    private String OutletAddress;
    @SerializedName("OutletZipcode")
    @Expose
    private String OutletZipcode;

    public String getOutletName() {
        return OutletName;
    }

    public void setOutletName(String outletName) {
        OutletName = outletName;
    }

    public String getOutletAddress() {
        return OutletAddress;
    }

    public void setOutletAddress(String outletAddress) {
        OutletAddress = outletAddress;
    }

    public String getOutletZipcode() {
        return OutletZipcode;
    }

    public void setOutletZipcode(String outletZipcode) {
        OutletZipcode = outletZipcode;
    }

    /**
     * @return The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name The Name
     */
    public void setName(String Name) {
        this.Name = Name;
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
     * @return The Purpose
     */
    public String getPurpose() {
        return Purpose;
    }

    /**
     * @param Purpose The Purpose
     */
    public void setPurpose(String Purpose) {
        this.Purpose = Purpose;
    }

    /**
     * @return The Message
     */
    public String getMessage() {
        return Message;
    }

    /**
     * @param Message The Message
     */
    public void setMessage(String Message) {
        this.Message = Message;
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
     * @return The CompanyName
     */
    public String getCompanyName() {
        return CompanyName;
    }

    /**
     * @param CompanyName The CompanyName
     */
    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
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
     * @return The Subject
     */
    public String getSubject() {
        return Subject;
    }

    /**
     * @param Subject The Subject
     */
    public void setSubject(String Subject) {
        this.Subject = Subject;
    }


}