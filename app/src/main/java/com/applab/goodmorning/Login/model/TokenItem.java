package com.applab.goodmorning.Login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 7/4/2016.
 */
public class TokenItem {

    @SerializedName("SystemCode")
    @Expose
    private Integer SystemCode;
    @SerializedName("PageNo")
    @Expose
    private Integer PageNo;
    @SerializedName("NoPerPage")
    @Expose
    private Integer NoPerPage;
    @SerializedName("SystemMessage")
    @Expose
    private String SystemMessage;
    @SerializedName("SystemDebugMessage")
    @Expose
    private String SystemDebugMessage;
    @SerializedName("items")
    @Expose
    private List<Token> items = new ArrayList<Token>();

    /**
     *
     * @return
     * The SystemCode
     */
    public Integer getSystemCode() {
        return SystemCode;
    }

    /**
     *
     * @param SystemCode
     * The SystemCode
     */
    public void setSystemCode(Integer SystemCode) {
        this.SystemCode = SystemCode;
    }

    /**
     *
     * @return
     * The PageNo
     */
    public Integer getPageNo() {
        return PageNo;
    }

    /**
     *
     * @param PageNo
     * The PageNo
     */
    public void setPageNo(Integer PageNo) {
        this.PageNo = PageNo;
    }

    /**
     *
     * @return
     * The NoPerPage
     */
    public Integer getNoPerPage() {
        return NoPerPage;
    }

    /**
     *
     * @param NoPerPage
     * The NoPerPage
     */
    public void setNoPerPage(Integer NoPerPage) {
        this.NoPerPage = NoPerPage;
    }

    /**
     *
     * @return
     * The SystemMessage
     */
    public String getSystemMessage() {
        return SystemMessage;
    }

    /**
     *
     * @param SystemMessage
     * The SystemMessage
     */
    public void setSystemMessage(String SystemMessage) {
        this.SystemMessage = SystemMessage;
    }

    /**
     *
     * @return
     * The SystemDebugMessage
     */
    public String getSystemDebugMessage() {
        return SystemDebugMessage;
    }

    /**
     *
     * @param SystemDebugMessage
     * The SystemDebugMessage
     */
    public void setSystemDebugMessage(String SystemDebugMessage) {
        this.SystemDebugMessage = SystemDebugMessage;
    }

    /**
     *
     * @return
     * The items
     */
    public List<Token> getTokens() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setTokens(List<Token> items) {
        this.items = items;
    }

}