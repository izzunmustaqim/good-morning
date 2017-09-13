package com.applab.goodmorning.Register.webapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.Login.activity.LoginActivity;
import com.applab.goodmorning.Login.model.TokenItem;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.database.CRUDHelper;
import com.applab.goodmorning.Register.model.CountryItem;
import com.applab.goodmorning.Register.model.Register;
import com.applab.goodmorning.Register.model.RegisteredItem;
import com.applab.goodmorning.Register.provider.CountryProvider;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;
import com.google.gson.Gson;

import org.apache.http.entity.ContentType;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 29/3/2016
 * -- Description: HttpHelper.java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class HttpHelper {
    public static void register(Context context, String TAG, final Register register) {
        GsonRequest<TokenItem> mGsonRequest = new GsonRequest<TokenItem>(
                Request.Method.POST,
                context.getString(R.string.base_url) + "Account/Register",
                TokenItem.class,
                null,
                responseRegister(context, TAG),
                errorRegister(context, TAG)) {

            @Override
            public String getBodyContentType() {
                return ContentType.APPLICATION_JSON.toString();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                String json = gson.toJson(register);
                return json.getBytes();
            }
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<TokenItem> responseRegister(final Context context, final String TAG) {
        return new Response.Listener<TokenItem>() {
            @Override
            public void onResponse(TokenItem response) {
                if (response.getSystemCode() != 400){
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                } else {
                    Utilities.showError(context,"",response.getSystemMessage());
                }
            }
        };
    }

    public static Response.ErrorListener errorRegister(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent intent = new Intent(TAG);
                intent.putExtra("failed", true);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                Utilities.serverHandlingError(context, error);
            }
        };
    }

    public static void getCountry(Context context, String TAG) {
        GsonRequest<CountryItem> mGsonRequest = new GsonRequest<CountryItem>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Country/List",
                CountryItem.class,
                null,
                responseCountry(context, TAG),
                errorCountry(context, TAG)) {


        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<CountryItem> responseCountry(final Context context, final String TAG) {
        return new Response.Listener<CountryItem>() {
            @Override
            public void onResponse(CountryItem response) {
                context.getContentResolver().delete(CountryProvider.CONTENT_URI, null, null);
                CRUDHelper.insertCountry(context, TAG, response);
            }
        };
    }

    public static Response.ErrorListener errorCountry(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent intent = new Intent(TAG);
                intent.putExtra("failed", true);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                Utilities.serverHandlingError(context, error);
            }
        };
    }

}
