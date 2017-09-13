package com.applab.goodmorning.Utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * -- =============================================
 * -- Author     : Edwin Cheong
 * -- Create date: 19/1/2016
 * -- Description: AssigneeList .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class DBHelper extends SQLiteOpenHelper {
    //Database Applab
    public static final String DATABASE_NAME = "applab.db";

    //Table Dashboard
    public static final String MENU_TABLE_NAME = "applab_dashboard";
    public static final String MENU_COLUMN_ID = "id";
    public static final String MENU_COLUMN_TITLE = "title";
    public static final String MENU_COLUMN_NO = "no";

    //Table Message Setting
    public static final String MESSAGE_TABLE_NAME = "applab_message";
    public static final String MESSAGE_COLUMN_ID = "id";
    public static final String MESSAGE_COLUMN_CODE = "code";
    public static final String MESSAGE_COLUMN_MESSAGE = "message";
    public static final String MESSAGE_COLUMN_STATUS = "status";
    public static final String MESSAGE_COLUMN_CREATED_DATE = "created_date";
    public static final String MESSAGE_COLUMN_UPDATED_DATE = "updated_date";

    //Table Token
    public static final String TOKEN_TABLE_NAME = "applab_token";
    public static final String TOKEN_COLUMN_ID = "id";
    public static final String TOKEN_COLUMN_SALUTATION = "salutation";
    public static final String TOKEN_COLUMN_FIRST_NAME = "first_name";
    public static final String TOKEN_COLUMN_LAST_NAME = "last_name";
    public static final String TOKEN_COLUMN_EMAIL = "email";
    public static final String TOKEN_COLUMN_COMPANY = "company";
    public static final String TOKEN_COLUMN_CONTACT_NO = "contact_no";
    public static final String TOKEN_COLUMN_COUNTRY_ID = "country_id";
    public static final String TOKEN_COLUMN_COUNTRY = "country";
    public static final String TOKEN_COLUMN_DEF_ADDRESS = "def_address";
    public static final String TOKEN_COLUMN_DEF_ZIPCODE = "def_zipcode";
    public static final String TOKEN_COLUMN_DEF_CITY = "def_city";
    public static final String TOKEN_COLUMN_DEF_STATE = "def_state";
    public static final String TOKEN_COLUMN_DEF_COUNTRY = "def_country";
    public static final String TOKEN_COLUMN_DEF_COUNTRY_ID = "def_country_id";
    public static final String TOKEN_COLUMN_DEF_STATE_CODE = "def_state_code";
    public static final String TOKEN_COLUMN_ADDRESS = "address";
    public static final String TOKEN_COLUMN_CITY = "city";
    public static final String TOKEN_COLUMN_STATE_CODE = "state_code";
    public static final String TOKEN_COLUMN_STATE = "state";
    public static final String TOKEN_COLUMN_ZIP_CODE = "zip_code";
    public static final String TOKEN_COLUMN_PASSWORD_LAST_CHANGE_DATE = "password_last_change_date";
    public static final String TOKEN_COLUMN_LAST_UPDATE_DATE = "last_update_date";
    public static final String TOKEN_COLUMN_ACCESS_TOKEN = "access_token";
    public static final String TOKEN_COLUMN_USER_ID = "user_id";
    public static final String TOKEN_COLUMN_EXPIRED_DATE = "expired_date";

    //Table Account
    public static final String ACCOUNT_TABLE_NAME = "applab_account";
    public static final String ACCOUNT_COLUMN_ID = "id";
    public static final String ACCOUNT_COLUMN_USER_ID = "user_id";
    public static final String ACCOUNT_COLUMN_USER_NAME = "user_name";
    public static final String ACCOUNT_COLUMN_SALUTATION = "salutation";
    public static final String ACCOUNT_COLUMN_FIRST_NAME = "first_name";
    public static final String ACCOUNT_COLUMN_LAST_NAME = "last_name";
    public static final String ACCOUNT_COLUMN_EMAIL = "email";
    public static final String ACCOUNT_COLUMN_COMPANY = "company";
    public static final String ACCOUNT_COLUMN_CONTACT_NO = "contact_no";
    public static final String ACCOUNT_COLUMN_ADDRESS = "address";
    public static final String ACCOUNT_COLUMN_ZIPCODE = "zipcode";
    public static final String ACCOUNT_COLUMN_CITY = "city";
    public static final String ACCOUNT_COLUMN_COUNTRY_ID = "country_id";
    public static final String ACCOUNT_COLUMN_STATE_CODE = "state_code";
    public static final String ACCOUNT_COLUMN_STATE = "state";
    public static final String ACCOUNT_COLUMN_COUNTRY = "country";
    public static final String ACCOUNT_COLUMN_DEF_ADDRESS = "def_address";
    public static final String ACCOUNT_COLUMN_DEF_ZIPCODE = "def_zipcode";
    public static final String ACCOUNT_COLUMN_DEF_CITY = "def_city";
    public static final String ACCOUNT_COLUMN_DEF_STATE = "def_state";
    public static final String ACCOUNT_COLUMN_DEF_COUNTRY = "def_country";
    public static final String ACCOUNT_COLUMN_DEF_COUNTRY_ID = "def_country_id";
    public static final String ACCOUNT_COLUMN_DEF_STATE_CODE = "def_state_code";
    public static final String ACCOUNT_COLUMN_PASSWORD_LAST_CHAGE_DATE = "password_last_change_date";
    public static final String ACCOUNT_COLUMN_LAST_UPDATE_DATE = "last_update_date";

    //Table Products
    public static final String PRODUCT_TABLE_NAME = "applab_product";
    public static final String PRODUCT_COLUMN_ID = "id";
    public static final String PRODUCT_COLUMN_PRODUCT_ID = "product_id";
    public static final String PRODUCT_COLUMN_TITLE = "product_title";
    public static final String PRODUCT_COLUMN_SUB_TITLE = "product_sub_title";
    public static final String PRODUCT_COLUMN_DESCRIPTION = "product_description";
    public static final String PRODUCT_COLUMN_WEIGHT = "product_weight";
    public static final String PRODUCT_COLUMN_FORMULA = "product_formula";
    public static final String PRODUCT_COLUMN_PRICE = "product_price";
    public static final String PRODUCT_COLUMN_IMAGE = "product_image";
    public static final String PRODUCT_COLUMN_IS_PROMOTION = "Promotion";
    public static final String PRODUCT_COLUMN_PROMOTION_PRICE = "promotion_price";
    public static final String PRODUCT_COLUMN_CREATE_DATE = "create_date";

    //Table Country
    public static final String COUNTRY_TABLE_NAME = "applab_country";
    public static final String COUNTRY_COLUMN_ID = "id";
    public static final String COUNTRY_COLUMN_COUNTRY_ID = "country_id";
    public static final String COUNTRY_COLUMN_CODE = "code";
    public static final String COUNTRY_COLUMN_NAME = "name";
    public static final String COUNTRY_COLUMN_CURRENCY = "currency";

    //Table StateItem
    public static final String STATE_TABLE_NAME = "applab_state";
    public static final String STATE_COLUMN_ID = "id";
    public static final String STATE_COLUMN_COUNTRY_ID = "country_id";
    public static final String STATE_COLUMN_STATE_ID = "state_id";
    public static final String STATE_COLUMN_CODE = "code";
    public static final String STATE_COLUMN_NAME = "name";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                " CREATE TABLE IF NOT EXISTS " + MENU_TABLE_NAME +
                        "(" + MENU_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        "" + MENU_COLUMN_TITLE + " TEXT," +
                        "" + MENU_COLUMN_NO + " INTEGER) "
        );

        db.execSQL(
                " CREATE TABLE IF NOT EXISTS " + MESSAGE_TABLE_NAME +
                        "(" + MESSAGE_COLUMN_ID + " integer primary key," +
                        "" + MESSAGE_COLUMN_CODE + " text," +
                        "" + MESSAGE_COLUMN_MESSAGE + " text," +
                        "" + MESSAGE_COLUMN_STATUS + " text," +
                        "" + MESSAGE_COLUMN_CREATED_DATE + " text," +
                        "" + MESSAGE_COLUMN_UPDATED_DATE + " text) "
        );

        db.execSQL(
                " CREATE TABLE IF NOT EXISTS " + TOKEN_TABLE_NAME +
                        "(" + TOKEN_COLUMN_ID + " integer primary key, " +
                        "" + TOKEN_COLUMN_ACCESS_TOKEN + " text, " +
                        "" + TOKEN_COLUMN_SALUTATION + " text, " +
                        "" + TOKEN_COLUMN_FIRST_NAME + " text, " +
                        "" + TOKEN_COLUMN_LAST_NAME + " text, " +
                        "" + TOKEN_COLUMN_EMAIL + " text, " +
                        "" + TOKEN_COLUMN_COMPANY + " text, " +
                        "" + TOKEN_COLUMN_CONTACT_NO + " text, " +
                        "" + TOKEN_COLUMN_COUNTRY + " text, " +
                        "" + TOKEN_COLUMN_COUNTRY_ID + " text, " +
                        "" + TOKEN_COLUMN_DEF_ADDRESS + " text, " +
                        "" + TOKEN_COLUMN_DEF_ZIPCODE + " text, " +
                        "" + TOKEN_COLUMN_DEF_CITY + " text, " +
                        "" + TOKEN_COLUMN_DEF_STATE + " text, " +
                        "" + TOKEN_COLUMN_DEF_COUNTRY + " text, " +
                        "" + TOKEN_COLUMN_DEF_COUNTRY_ID + " text, " +
                        "" + TOKEN_COLUMN_DEF_STATE_CODE + " text, " +
                        "" + TOKEN_COLUMN_ADDRESS + " text, " +
                        "" + TOKEN_COLUMN_CITY + " text, " +
                        "" + TOKEN_COLUMN_STATE_CODE + " text, " +
                        "" + TOKEN_COLUMN_STATE + " text, " +
                        "" + TOKEN_COLUMN_ZIP_CODE + " text, " +
                        "" + TOKEN_COLUMN_PASSWORD_LAST_CHANGE_DATE + " text, " +
                        "" + TOKEN_COLUMN_LAST_UPDATE_DATE + " text, " +
                        "" + TOKEN_COLUMN_USER_ID + " text, " +
                        "" + TOKEN_COLUMN_EXPIRED_DATE + " text) "
        );

        db.execSQL(
                " CREATE TABLE IF NOT EXISTS " + ACCOUNT_TABLE_NAME +
                        "(" + ACCOUNT_COLUMN_ID + " integer primary key, " +
                        "" + ACCOUNT_COLUMN_USER_ID + " text, " +
                        "" + ACCOUNT_COLUMN_USER_NAME + " text, " +
                        "" + ACCOUNT_COLUMN_SALUTATION + " text, " +
                        "" + ACCOUNT_COLUMN_FIRST_NAME + " text, " +
                        "" + ACCOUNT_COLUMN_LAST_NAME + " text, " +
                        "" + ACCOUNT_COLUMN_EMAIL + " text, " +
                        "" + ACCOUNT_COLUMN_COMPANY + " text, " +
                        "" + ACCOUNT_COLUMN_CONTACT_NO + " text, " +
                        "" + ACCOUNT_COLUMN_ADDRESS + " text, " +
                        "" + ACCOUNT_COLUMN_ZIPCODE + " text, " +
                        "" + ACCOUNT_COLUMN_CITY + " text, " +
                        "" + ACCOUNT_COLUMN_STATE + " text, " +
                        "" + ACCOUNT_COLUMN_STATE_CODE + " text, " +
                        "" + ACCOUNT_COLUMN_COUNTRY + " text, " +
                        "" + ACCOUNT_COLUMN_COUNTRY_ID + " text, " +
                        "" + ACCOUNT_COLUMN_DEF_ADDRESS + " text, " +
                        "" + ACCOUNT_COLUMN_DEF_ZIPCODE + " text, " +
                        "" + ACCOUNT_COLUMN_DEF_CITY + " text, " +
                        "" + ACCOUNT_COLUMN_DEF_STATE + " text, " +
                        "" + ACCOUNT_COLUMN_DEF_COUNTRY + " text, " +
                        "" + ACCOUNT_COLUMN_DEF_COUNTRY_ID + " text, " +
                        "" + ACCOUNT_COLUMN_DEF_STATE_CODE + " text, " +
                        "" + ACCOUNT_COLUMN_PASSWORD_LAST_CHAGE_DATE + " text, " +
                        "" + ACCOUNT_COLUMN_LAST_UPDATE_DATE + " text) "
        );

        db.execSQL(
                " CREATE TABLE IF NOT EXISTS " + PRODUCT_TABLE_NAME +
                        "(" + PRODUCT_COLUMN_ID + " integer primary key, " +
                        "" + PRODUCT_COLUMN_PRODUCT_ID + " text, " +
                        "" + PRODUCT_COLUMN_TITLE + " text, " +
                        "" + PRODUCT_COLUMN_SUB_TITLE + " text, " +
                        "" + PRODUCT_COLUMN_DESCRIPTION + " text, " +
                        "" + PRODUCT_COLUMN_WEIGHT + " text, " +
                        "" + PRODUCT_COLUMN_FORMULA + " text, " +
                        "" + PRODUCT_COLUMN_PRICE + " text, " +
                        "" + PRODUCT_COLUMN_IMAGE + " text, " +
                        "" + PRODUCT_COLUMN_IS_PROMOTION + " text, " +
                        "" + PRODUCT_COLUMN_PROMOTION_PRICE + " text, " +
                        "" + PRODUCT_COLUMN_CREATE_DATE + " text) "
        );


        db.execSQL(
                " CREATE TABLE IF NOT EXISTS " + COUNTRY_TABLE_NAME +
                        "(" + COUNTRY_COLUMN_ID + " integer primary key," +
                        "" + COUNTRY_COLUMN_COUNTRY_ID + " text," +
                        "" + COUNTRY_COLUMN_CODE + " text," +
                        "" + COUNTRY_COLUMN_NAME + " text," +
                        "" + COUNTRY_COLUMN_CURRENCY + " text) "
        );

        db.execSQL(
                " CREATE TABLE IF NOT EXISTS " + STATE_TABLE_NAME +
                        "(" + STATE_COLUMN_ID + " integer primary key," +
                        "" + STATE_COLUMN_STATE_ID + " text," +
                        "" + STATE_COLUMN_CODE + " text," +
                        "" + STATE_COLUMN_COUNTRY_ID + " text," +
                        "" + STATE_COLUMN_NAME + " text) "
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
