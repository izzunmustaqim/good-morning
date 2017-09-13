package com.applab.goodmorning.Register.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 29/3/2016
 * -- Description: Item.java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class Item {

    @SerializedName("Username")
    @Expose
    private String Username;
    @SerializedName("Email")
    @Expose
    private String Email;

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

}