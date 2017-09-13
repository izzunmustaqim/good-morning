package com.applab.goodmorning.Enquiry.webapi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.ContactUs.model.ContactUsItem;
import com.applab.goodmorning.Enquiry.activity.EnquiryActivity;
import com.applab.goodmorning.Enquiry.model.Enquiry;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;
import com.google.gson.Gson;

import org.apache.http.entity.ContentType;

/**
 * Created by user on 12/4/2016.
 */
public class HttpHelper {

    private static void sendLocalLock(boolean isLock, Context context) {
        Intent intent = new Intent(EnquiryActivity.class.getSimpleName());
        intent.putExtra("isLock", isLock);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void postEnquiry(Context context, String TAG, final Enquiry enquiry, String baseUrl) {
        sendLocalLock(true, context);
        GsonRequest<ContactUsItem> mGsonRequest = new GsonRequest<ContactUsItem>(
                Request.Method.POST,
                context.getString(R.string.base_url) + baseUrl,
                ContactUsItem.class,
                null,
                responseEventListListener(context, TAG),
                errorEventListListener(context, TAG)) {

            @Override
            public String getBodyContentType() {
                return ContentType.APPLICATION_JSON.toString();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return new Gson().toJson(enquiry).getBytes();
            }
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
                intent.putExtra("success", true);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                sendLocalLock(false, context);
                Utilities.showError(context, "", context.getString(R.string.success_send));
            }
        };
    }

    public static Response.ErrorListener errorEventListListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent intent = new Intent(TAG);
                intent.putExtra("failed", true);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                Utilities.serverHandlingError(context, error);
                sendLocalLock(false, context);
            }
        };
    }
}
