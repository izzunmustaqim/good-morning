package com.applab.goodmorning.Account.model;

/**
 * Created by User on 1/4/2016.
 */
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemList {

    @SerializedName("items")
    @Expose
    private List<Account> items = new ArrayList<Account>();


    /**
     *
     * @return
     * The items
     */
    public List<Account> getItems() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setItems(List<Account> items) {
        this.items = items;
    }

}