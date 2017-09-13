package com.applab.goodmorning.Account.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.LocalBroadcastManager;

import com.applab.goodmorning.Account.model.Account;
import com.applab.goodmorning.Account.model.ItemList;
import com.applab.goodmorning.Account.provider.AccountProvider;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.provider.TokenProvider;
import com.applab.goodmorning.Products.provider.ProductProvider;
import com.applab.goodmorning.Utilities.DBHelper;

/**
 * Created by user on 28-Mar-16.
 */
public class CRUDHelper {
    public static void insertAccount(Context context, ItemList itemList, String TAG) {
        DBHelper helper = new DBHelper(context);
        try {
            ContentValues[] contentValueses = new ContentValues[itemList.getItems().size()];
            for (int i = 0; i < itemList.getItems().size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.ACCOUNT_COLUMN_USER_ID, itemList.getItems().get(i).getUserId());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_USER_NAME, itemList.getItems().get(i).getUsername());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_SALUTATION, itemList.getItems().get(i).getSalutation());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_FIRST_NAME, itemList.getItems().get(i).getFirstName());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_LAST_NAME, itemList.getItems().get(i).getLastName());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_EMAIL, itemList.getItems().get(i).getEmail());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_COMPANY, itemList.getItems().get(i).getCompany());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_CONTACT_NO, itemList.getItems().get(i).getContactNo());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_ADDRESS, itemList.getItems().get(i).getAddress());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_ZIPCODE, itemList.getItems().get(i).getZipcode());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_CITY, itemList.getItems().get(i).getCity());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_COUNTRY_ID, itemList.getItems().get(i).getCountryId());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_STATE_CODE, itemList.getItems().get(i).getStateCode());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_STATE, itemList.getItems().get(i).getState());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_COUNTRY, itemList.getItems().get(i).getCountry());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_COUNTRY_ID, itemList.getItems().get(i).getCountryId());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_DEF_ADDRESS, itemList.getItems().get(i).getDefAddress());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_DEF_ZIPCODE, itemList.getItems().get(i).getDefZipcode());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_DEF_CITY, itemList.getItems().get(i).getDefCity());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_DEF_STATE, itemList.getItems().get(i).getDefState());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_DEF_COUNTRY, itemList.getItems().get(i).getDefCountry());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_DEF_STATE_CODE, itemList.getItems().get(i).getDefStateCode());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_PASSWORD_LAST_CHAGE_DATE, itemList.getItems().get(i).getPasswordLastChangeDate());
                contentValues.put(DBHelper.ACCOUNT_COLUMN_LAST_UPDATE_DATE, itemList.getItems().get(i).getLastUpdateDate());
                contentValueses[i] = contentValues;
            }
            boolean isEmpty = true;
            for (ContentValues contentValues : contentValueses) {
                if (contentValues != null) {
                    isEmpty = false;
                }
            }
            if (!isEmpty) {
                context.getContentResolver().bulkInsert(AccountProvider.CONTENT_URI, contentValueses);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Intent intent = new Intent(TAG);
            intent.putExtra("isFetched", true);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    public static Account getAccount(Context context) {
        Cursor cursor = null;
        Account account = null;
        try {
            cursor = context.getContentResolver().query(AccountProvider.CONTENT_URI, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                account = Account.getAccount(cursor, 0);
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return account;
    }
}
