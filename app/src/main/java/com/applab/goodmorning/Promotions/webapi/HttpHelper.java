package com.applab.goodmorning.Promotions.webapi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.Promotions.model.PromotionItem;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Welcome.model.BannerItems;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 13/4/2016
 * -- Description: HttpHelper .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class HttpHelper {
    public static void getPromotion(Context context, String TAG) {
        GsonRequest<PromotionItem> mGsonRequest = new GsonRequest<PromotionItem>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Promotion?countryId=" + Utilities.getCountryID(context),
                PromotionItem.class,
                null,
                responsePromotionItemListener(context, TAG),
                errorPromotionItemListener(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<PromotionItem> responsePromotionItemListener(final Context context, final String TAG) {
        return new Response.Listener<PromotionItem>() {
            @Override
            public void onResponse(PromotionItem response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("Promotion", response);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorPromotionItemListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.serverHandlingError(context, error);
            }
        };
    }
}
