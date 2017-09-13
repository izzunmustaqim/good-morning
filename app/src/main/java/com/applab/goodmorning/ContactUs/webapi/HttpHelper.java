package com.applab.goodmorning.ContactUs.webapi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.ContactUs.model.ContactUsItem;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;

/**
 * Created by user on 8/4/2016.
 */
public class HttpHelper {
    public static void getOutlet(Context context, String TAG, int loyalty) {
        String url = context.getString(R.string.base_url) + "Outlet/List?countryId=" +
                Utilities.getCountryID(context) + "&isLoyalty=" + loyalty + "";
        GsonRequest<ContactUsItem> mGsonRequest = new GsonRequest<ContactUsItem>(
                Request.Method.GET,
                url,
                ContactUsItem.class,
                null,
                responseEventListListener(context, TAG),
                errorEventListListener(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<ContactUsItem> responseEventListListener(final Context context, final String TAG) {
        return new Response.Listener<ContactUsItem>() {
            @Override
            public void onResponse(ContactUsItem response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("Outlets", response);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorEventListListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.serverHandlingError(context, error);
            }
        };
    }
}
