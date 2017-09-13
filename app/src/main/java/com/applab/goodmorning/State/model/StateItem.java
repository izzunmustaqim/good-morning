package com.applab.goodmorning.State.model;

/**
 * Created by user on 31-Mar-16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StateItem {

    @SerializedName("items")
    @Expose
    private List<State> items = new ArrayList<State>();

    /**
     *
     * @return
     * The items
     */
    public List<State> getItems() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setItems(List<State> items) {
        this.items = items;
    }

}