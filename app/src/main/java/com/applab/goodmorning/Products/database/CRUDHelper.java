package com.applab.goodmorning.Products.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.applab.goodmorning.Products.model.ProductItems;
import com.applab.goodmorning.Products.provider.ProductProvider;
import com.applab.goodmorning.Utilities.DBHelper;

/**
 * Created by user on 29-Mar-16.
 */
public class CRUDHelper {
    public static void insertProducts(ProductItems items, Context context, String TAG) {
        DBHelper helper = new DBHelper(context);
        try {
            ContentValues[] contentValueses = new ContentValues[items.getItems().size()];
            for (int i = 0; i < items.getItems().size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.PRODUCT_COLUMN_PRODUCT_ID, items.getItems().get(i).getId());
                contentValues.put(DBHelper.PRODUCT_COLUMN_TITLE, items.getItems().get(i).getProductTitle());
                contentValues.put(DBHelper.PRODUCT_COLUMN_SUB_TITLE, items.getItems().get(i).getProductSubTitle());
                contentValues.put(DBHelper.PRODUCT_COLUMN_DESCRIPTION, items.getItems().get(i).getProductDescription());
                contentValues.put(DBHelper.PRODUCT_COLUMN_PRICE, items.getItems().get(i).getPrice());
                contentValues.put(DBHelper.PRODUCT_COLUMN_IMAGE, items.getItems().get(i).getProductImage());
                contentValues.put(DBHelper.PRODUCT_COLUMN_IS_PROMOTION, items.getItems().get(i).getIsPromotion());
                contentValues.put(DBHelper.PRODUCT_COLUMN_PROMOTION_PRICE, items.getItems().get(i).getPromotionPrice());
                contentValues.put(DBHelper.PRODUCT_COLUMN_CREATE_DATE, items.getItems().get(i).getCreateDate());
                contentValueses[i] = contentValues;
            }
            boolean isEmpty = true;
            for (ContentValues contentValues : contentValueses) {
                if (contentValues != null) {
                    isEmpty = false;
                }
            }
            if (!isEmpty) {
                context.getContentResolver().bulkInsert(ProductProvider.CONTENT_URI, contentValueses);
            }
        } catch (Exception ex) {
            Log.i(TAG, "Content Provider Download Error :" + ex.toString());
        } finally {
            Intent intent = new Intent(TAG);
            intent.putExtra("isFetched", true);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

}
