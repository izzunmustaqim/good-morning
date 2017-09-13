package com.applab.goodmorning.Menu.provider;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.DBHelper;


/**
 * -- =============================================
 * -- Author     : Edwin Cheong
 * -- Create date: 3/3/2016
 * -- Description: Defining provider for DB
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class MenuProvider extends ContentProvider {
    private static String CONTENT_AUTHORITY = "com.applab.goodmorning.menu.provider";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    private static final String PATH_CONTACT = "menu";
    private static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + PATH_CONTACT;
    private static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + PATH_CONTACT;
    private static final int MENU = 1;
    private static final int MENU_ID = 2;
    private DBHelper dbHelper;
    private UriMatcher uriMatcher = null;
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CONTACT).build();

    public MenuProvider() {
        super();
    }

    public void setUriMatcher() {
        this.uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CONTACT, MENU);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CONTACT + "/#", MENU_ID);
    }

    public UriMatcher getUriMatcher() {
        return uriMatcher;
    }

    public static Uri setCalendarUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    public Cursor getNewsWithName(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        return queryBuilder.query(
                dbHelper.getReadableDatabase(),//the database to query on
                projection,//A list of which columns to return
                selection,// rows to return
                selectionArgs,
                null,//groupBy
                null,//having
                sortOrder//orderby
        );
    }

    @Override
    public boolean onCreate() {
        CONTENT_AUTHORITY = getContext().getResources().getString(R.string.menu_provider);
        dbHelper = new DBHelper(getContext());
        this.setUriMatcher();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;

        switch (uriMatcher.match(uri)) {
            case MENU:
                cursor = dbHelper.getReadableDatabase().query(
                        dbHelper.MENU_TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case MENU_ID:
                cursor = getNewsWithName(uri, projection, selection, selectionArgs, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case MENU:
                return CONTENT_TYPE;
            case MENU_ID:
                return CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int match = getUriMatcher().match(uri);
        int numRowsInsert = 0;
        switch (match) {
            case MENU:
                db.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(dbHelper.MENU_TABLE_NAME, null, value);
                        if (_id != -1) {
                            numRowsInsert++;
                        }
                    }
                    // To commit the transaction
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                break;
            default:
                return super.bulkInsert(uri, values);
        }

        if (numRowsInsert > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numRowsInsert;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri returnUri = null;
        int match = this.getUriMatcher().match(uri);
        long rowID;
        switch (match) {
            case MENU:
                rowID = db.insert(dbHelper.MENU_TABLE_NAME, "", values);
                if (rowID > 0) {
                    returnUri = setCalendarUri(rowID);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int match = getUriMatcher().match(uri);
        int rowDeleted;

        switch (match) {
            case MENU:
                rowDeleted = db.delete(dbHelper.MENU_TABLE_NAME, selection, selectionArgs);
                break;
            case MENU_ID:
                String id = uri.getPathSegments().get(1);
                rowDeleted = db.delete(dbHelper.MENU_TABLE_NAME, dbHelper.MENU_COLUMN_ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (selection == null || rowDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case MENU:
                count = db.update(dbHelper.MENU_TABLE_NAME, values,
                        selection, selectionArgs);
                break;
            case MENU_ID:
                count = db.update(dbHelper.MENU_TABLE_NAME, values, dbHelper.MENU_COLUMN_ID +
                        " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}

