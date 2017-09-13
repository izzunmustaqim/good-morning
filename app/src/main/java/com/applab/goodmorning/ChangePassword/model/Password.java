package com.applab.goodmorning.ChangePassword.model;

/**
 * Created by User on 6/4/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Password {

    @SerializedName("OldPassword")
    @Expose
    private String OldPassword;
    @SerializedName("NewPassword")
    @Expose
    private String NewPassword;
    @SerializedName("ConfirmPassword")
    @Expose
    private String ConfirmPassword;

    /**
     * @return The OldPassword
     */
    public String getOldPassword() {
        return OldPassword;
    }

    /**
     * @param OldPassword The OldPassword
     */
    public void setOldPassword(String OldPassword) {
        this.OldPassword = OldPassword;
    }

    /**
     * @return The NewPassword
     */
    public String getNewPassword() {
        return NewPassword;
    }

    /**
     * @param NewPassword The NewPassword
     */
    public void setNewPassword(String NewPassword) {
        this.NewPassword = NewPassword;
    }

    /**
     * @return The ConfirmPassword
     */
    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    /**
     * @param ConfirmPassword The ConfirmPassword
     */
    public void setConfirmPassword(String ConfirmPassword) {
        this.ConfirmPassword = ConfirmPassword;
    }

}
