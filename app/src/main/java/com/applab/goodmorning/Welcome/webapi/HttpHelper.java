package com.applab.goodmorning.Welcome.webapi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Welcome.model.BannerItems;

import java.util.HashMap;

/**
 * Created by user on 31/3/2016.
 */
public class HttpHelper {
    public static void getBanner(Context context, String TAG) {
        GsonRequest<BannerItems> mGsonRequest = new GsonRequest<BannerItems>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Banner/List?countryId=" + Utilities.getCountryID(context) + "",
                BannerItems.class,
                null,
                responseBannerItemsListener(context, TAG),
                errorBannerItemsListener(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<BannerItems> responseBannerItemsListener(final Context context, final String TAG) {
        return new Response.Listener<BannerItems>() {
            @Override
            public void onResponse(BannerItems response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("Banners", response);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorBannerItemsListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.serverHandlingError(context, error);
            }
        };
    }
}
