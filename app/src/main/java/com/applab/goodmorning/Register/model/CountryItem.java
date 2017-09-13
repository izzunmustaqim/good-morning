package com.applab.goodmorning.Register.model;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 29/3/2016
 * -- Description: CountryItem .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;


public class CountryItem {


    @Expose
    private List<Country> items = new ArrayList<Country>();

    /**
     *
     * @return
     * The items
     */
    public List<Country> getCountrys() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setCountrys(List<Country> items) {
        this.items = items;
    }

}