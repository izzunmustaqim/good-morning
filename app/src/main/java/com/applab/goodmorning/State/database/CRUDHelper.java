package com.applab.goodmorning.State.database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.applab.goodmorning.R;
import com.applab.goodmorning.State.model.StateItem;
import com.applab.goodmorning.State.provider.StateProvider;
import com.applab.goodmorning.Utilities.DBHelper;
import com.applab.goodmorning.Utilities.Utilities;

/**
 * Created by user on 31-Mar-16.
 */
public class CRUDHelper {
    public static void insertState(Context context, String TAG, StateItem stateItem, int countryId) {
        DBHelper helper = new DBHelper(context);
        try {
            ContentValues[] contentValueses = new ContentValues[stateItem.getItems().size()];
            context.getContentResolver().delete(StateProvider.CONTENT_URI, DBHelper.STATE_COLUMN_COUNTRY_ID + "=?", new String[]{String.valueOf(countryId)});
            for (int i = 0; i < stateItem.getItems().size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.STATE_COLUMN_STATE_ID, stateItem.getItems().get(i).getId());
                contentValues.put(DBHelper.STATE_COLUMN_CODE, stateItem.getItems().get(i).getStateCode());
                contentValues.put(DBHelper.STATE_COLUMN_COUNTRY_ID, countryId);
                contentValues.put(DBHelper.STATE_COLUMN_NAME, stateItem.getItems().get(i).getStateName());
                contentValueses[i] = contentValues;
            }
            boolean isEmpty = true;
            for (ContentValues contentValues : contentValueses) {
                if (contentValues != null) {
                    isEmpty = false;
                }
            }
            if (!isEmpty) {
                context.getContentResolver().bulkInsert(StateProvider.CONTENT_URI, contentValueses);
            }
        } catch (Exception ex) {
            Log.i(TAG, "Content Provider Download Error :" + ex.toString());
        }
    }
}
