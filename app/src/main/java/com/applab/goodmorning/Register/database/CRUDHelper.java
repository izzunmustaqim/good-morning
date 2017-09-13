package com.applab.goodmorning.Register.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.applab.goodmorning.Register.model.Country;
import com.applab.goodmorning.Register.model.CountryItem;
import com.applab.goodmorning.Register.provider.CountryProvider;
import com.applab.goodmorning.Utilities.DBHelper;

import java.util.ArrayList;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 29/3/2016
 * -- Description: CRUDHelper.java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class CRUDHelper {
    public static void insertCountry(Context context, String TAG, CountryItem item) {
        DBHelper helper = new DBHelper(context);
        try {
            ArrayList<ContentValues> contentValuesArrayList = new ArrayList<>();
            for (int i = 0; i < item.getCountrys().size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.COUNTRY_COLUMN_COUNTRY_ID, item.getCountrys().get(i).getId());
                contentValues.put(DBHelper.COUNTRY_COLUMN_CODE, item.getCountrys().get(i).getCountryCode());
                contentValues.put(DBHelper.COUNTRY_COLUMN_NAME, item.getCountrys().get(i).getCountryName());
                contentValues.put(DBHelper.COUNTRY_COLUMN_CURRENCY, item.getCountrys().get(i).getCurrency());
                contentValuesArrayList.add(contentValues);
            }
            ContentValues[] contentValueses = new ContentValues[contentValuesArrayList.size()];
            contentValueses = contentValuesArrayList.toArray(contentValueses);
            boolean isEmpty = true;
            for (Object ob : contentValueses) {
                if (ob != null) {
                    isEmpty = false;
                }
            }
            long rowsInserted = 0;
            if (!isEmpty) {
                rowsInserted = context.getContentResolver().bulkInsert(CountryProvider.CONTENT_URI, contentValueses);
            }

        } catch (Exception ex) {
            Log.i(TAG, "Content Provider Error :" + ex.toString());
        }
    }

    public static Country getCountry(Context context, int countryId) {
        Cursor cursor = null;
        Country country = null;
        try {
            cursor = context.getContentResolver().query(CountryProvider.CONTENT_URI, null, DBHelper.COUNTRY_COLUMN_COUNTRY_ID + "=?", new String[]{String.valueOf(countryId)}, null);
            if (cursor != null) {
                country = Country.getCountry(cursor, 0);
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return country;
    }
}
