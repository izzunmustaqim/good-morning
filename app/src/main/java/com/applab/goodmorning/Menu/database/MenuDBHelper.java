package com.applab.goodmorning.Menu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.applab.goodmorning.Menu.provider.MenuProvider;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.DBHelper;

/**
 * -- =============================================
 * -- Author     : Edwin Cheong
 * -- Create date: 3/3/2016
 * -- Description: CRUD HELPER for menu
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class MenuDBHelper {
    public static void insertMenu(Context context) {
        if (getTotalMenuRow(context) == 0) {
            ContentValues[] contentValueses = new ContentValues[11];
            String[] title = {
                    context.getString(R.string.title_activity_home),
                    context.getString(R.string.title_activity_products),
                    context.getString(R.string.title_activity_photos),
                    context.getString(R.string.title_activity_videos),
                    context.getString(R.string.title_activity_news),
                    context.getString(R.string.title_activity_promotions),
                    context.getString(R.string.title_activity_events),
                    context.getString(R.string.title_activity_contact_us),
                    context.getString(R.string.title_activity_enquirys),
                    context.getString(R.string.title_activity_our_story),
                    context.getString(R.string.title_activity_settings)
            };
            for (int i = 0; i < 11; i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.MENU_COLUMN_TITLE, title[i]);
                contentValues.put(DBHelper.MENU_COLUMN_NO, 0);
                contentValueses[i] = contentValues;
            }
            context.getContentResolver().bulkInsert(MenuProvider.CONTENT_URI, contentValueses);
        }
    }

    public static int getTotalMenuRow(Context context) {
        Cursor cursor = null;
        int total = 0;
        try {
            cursor = context.getContentResolver().query(MenuProvider.CONTENT_URI,
                    null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                total = cursor.getCount();
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return total;
    }

    public static void updateMenu(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.MENU_COLUMN_TITLE, context.getString(R.string.title_activity_home));
        context.getContentResolver().update(MenuProvider.CONTENT_URI, contentValues, DBHelper.MENU_COLUMN_ID + "=?", new String[]{String.valueOf(1)});

        contentValues = new ContentValues();
        contentValues.put(DBHelper.MENU_COLUMN_TITLE, context.getString(R.string.title_activity_products));
        context.getContentResolver().update(MenuProvider.CONTENT_URI, contentValues, DBHelper.MENU_COLUMN_ID + "=?", new String[]{String.valueOf(2)});

        contentValues = new ContentValues();
        contentValues.put(DBHelper.MENU_COLUMN_TITLE, context.getString(R.string.title_activity_photos));
        context.getContentResolver().update(MenuProvider.CONTENT_URI, contentValues, DBHelper.MENU_COLUMN_ID + "=?", new String[]{String.valueOf(3)});

        contentValues = new ContentValues();
        contentValues.put(DBHelper.MENU_COLUMN_TITLE, context.getString(R.string.title_activity_videos));
        context.getContentResolver().update(MenuProvider.CONTENT_URI, contentValues, DBHelper.MENU_COLUMN_ID + "=?", new String[]{String.valueOf(4)});

        contentValues = new ContentValues();
        contentValues.put(DBHelper.MENU_COLUMN_TITLE, context.getString(R.string.title_activity_news));
        context.getContentResolver().update(MenuProvider.CONTENT_URI, contentValues, DBHelper.MENU_COLUMN_ID + "=?", new String[]{String.valueOf(5)});

        contentValues = new ContentValues();
        contentValues.put(DBHelper.MENU_COLUMN_TITLE,context.getString(R.string.title_activity_promotions));
        context.getContentResolver().update(MenuProvider.CONTENT_URI,contentValues,DBHelper.MENU_COLUMN_ID + "=?", new String[]{String.valueOf(6)});

        contentValues = new ContentValues();
        contentValues.put(DBHelper.MENU_COLUMN_TITLE, context.getString(R.string.title_activity_events));
        context.getContentResolver().update(MenuProvider.CONTENT_URI, contentValues, DBHelper.MENU_COLUMN_ID + "=?", new String[]{String.valueOf(7)});

        contentValues = new ContentValues();
        contentValues.put(DBHelper.MENU_COLUMN_TITLE, context.getString(R.string.title_activity_contact_us));
        context.getContentResolver().update(MenuProvider.CONTENT_URI, contentValues, DBHelper.MENU_COLUMN_ID + "=?", new String[]{String.valueOf(8)});

        contentValues = new ContentValues();
        contentValues.put(DBHelper.MENU_COLUMN_TITLE,context.getString(R.string.title_activity_enquirys));
        context.getContentResolver().update(MenuProvider.CONTENT_URI,contentValues,DBHelper.MENU_COLUMN_ID + "=?", new String[]{String.valueOf(9)});

        contentValues = new ContentValues();
        contentValues.put(DBHelper.MENU_COLUMN_TITLE,context.getString(R.string.title_activity_our_story));
        context.getContentResolver().update(MenuProvider.CONTENT_URI,contentValues,DBHelper.MENU_COLUMN_ID + "=?", new String[]{String.valueOf(10)});

        contentValues = new ContentValues();
        contentValues.put(DBHelper.MENU_COLUMN_TITLE, context.getString(R.string.title_activity_settings));
        context.getContentResolver().update(MenuProvider.CONTENT_URI, contentValues, DBHelper.MENU_COLUMN_ID + "=?", new String[]{String.valueOf(11)});
    }
}
