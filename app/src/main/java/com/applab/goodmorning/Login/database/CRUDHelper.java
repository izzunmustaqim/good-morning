package com.applab.goodmorning.Login.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.provider.TokenProvider;
import com.applab.goodmorning.Utilities.DBHelper;

/**
 * -- =============================================
 * -- Author     : Edwin Cheong
 * -- Create date: 28/3/2016
 * -- Description: CRUDHelper .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class CRUDHelper {
    public static void insertToken(Context context, Token token) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TOKEN_COLUMN_ACCESS_TOKEN, token.getToken());
        contentValues.put(DBHelper.TOKEN_COLUMN_USER_ID, token.getUserId());
        contentValues.put(DBHelper.TOKEN_COLUMN_SALUTATION, token.getSalutation());
        contentValues.put(DBHelper.TOKEN_COLUMN_FIRST_NAME, token.getFirstName());
        contentValues.put(DBHelper.TOKEN_COLUMN_LAST_NAME, token.getLastName());
        contentValues.put(DBHelper.TOKEN_COLUMN_EMAIL, token.getEmail());
        contentValues.put(DBHelper.TOKEN_COLUMN_COMPANY, token.getCompany());
        contentValues.put(DBHelper.TOKEN_COLUMN_CONTACT_NO, token.getContactNo());
        contentValues.put(DBHelper.TOKEN_COLUMN_COUNTRY, token.getCountry());
        contentValues.put(DBHelper.TOKEN_COLUMN_DEF_ADDRESS, token.getDefAddress());
        contentValues.put(DBHelper.TOKEN_COLUMN_DEF_ZIPCODE, token.getDefZipcode());
        contentValues.put(DBHelper.TOKEN_COLUMN_DEF_CITY, token.getDefState());
        contentValues.put(DBHelper.TOKEN_COLUMN_DEF_STATE, token.getDefState());
        contentValues.put(DBHelper.TOKEN_COLUMN_DEF_COUNTRY, token.getDefCountry());
        contentValues.put(DBHelper.TOKEN_COLUMN_ADDRESS, token.getAddress());
        contentValues.put(DBHelper.TOKEN_COLUMN_CITY, token.getCity());
        contentValues.put(DBHelper.TOKEN_COLUMN_STATE, token.getState());
        contentValues.put(DBHelper.TOKEN_COLUMN_ZIP_CODE, token.getZipcode());
        contentValues.put(DBHelper.TOKEN_COLUMN_LAST_UPDATE_DATE, token.getLastUpdateDate());
        contentValues.put(DBHelper.TOKEN_COLUMN_PASSWORD_LAST_CHANGE_DATE, token.getPasswordLastChangeDate());
        contentValues.put(DBHelper.TOKEN_COLUMN_EXPIRED_DATE, token.getExpiryDate());
        context.getContentResolver().insert(TokenProvider.CONTENT_URI, contentValues);
    }

    public static Token getToken(Context context) {
        Cursor cursor = null;
        Token token = null;
        String mOrder = DBHelper.TOKEN_COLUMN_ID + " ASC " + "LIMIT 1";

        try {
            cursor = context.getContentResolver().query(TokenProvider.CONTENT_URI, null, null, null, mOrder);
            if (cursor != null && cursor.moveToFirst()) {
                token = Token.getToken(cursor, 0);
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return token;
    }
}
