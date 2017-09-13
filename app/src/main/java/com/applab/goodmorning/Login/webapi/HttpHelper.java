package com.applab.goodmorning.Login.webapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.Account.model.Account;
import com.applab.goodmorning.Account.model.ItemList;
import com.applab.goodmorning.Account.provider.AccountProvider;
import com.applab.goodmorning.Login.database.CRUDHelper;
import com.applab.goodmorning.Login.model.TokenItem;

import com.applab.goodmorning.Login.provider.TokenProvider;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Utilities.dialog.GeneralDialogFragment;
import com.applab.goodmorning.Welcome.activity.WelcomeActivity;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;

import org.apache.http.entity.ContentType;

import java.util.List;

import eu.janmuller.android.simplecropimage.Util;

/**
 * -- =============================================
 * -- Author     : Edwin Cheong
 * -- Create date: 28/3/2016
 * -- Description: Webapi .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class HttpHelper {
    public static void loginApi(Context context, String TAG, final String username, final String password) {
        GsonRequest<TokenItem> mGsonRequest = new GsonRequest<TokenItem>(
                Request.Method.POST,
                context.getString(R.string.base_url) + "Account/Login",
                TokenItem.class,
                null,
                responseLoginListener(context, TAG),
                errorLoginListener(context, TAG)) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                String httpbody = "username=" + username + "&password=" + password;
                return httpbody.getBytes();
            }
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<TokenItem> responseLoginListener(final Context context, final String TAG) {
        return new Response.Listener<TokenItem>() {
            @Override
            public void onResponse(TokenItem response) {
                Integer errorcode = 400;
                if (!response.getSystemCode().equals(errorcode)) {
                    context.getContentResolver().delete(TokenProvider.CONTENT_URI, null, null);
                    context.getContentResolver().delete(AccountProvider.CONTENT_URI, null, null);
                    CRUDHelper.insertToken(context, response.getTokens().get(0));
                    com.applab.goodmorning.Account.webapi.HttpHelper.getMyProfileApi(context,TAG);
                    Intent intent = new Intent(context, WelcomeActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                } else if (response.getSystemDebugMessage().equals(context.getString(R.string.error_login))) {
                    GeneralDialogFragment generalDialogFragment = GeneralDialogFragment.newInstance(context,
                            context.getString(R.string.error_login), context.getString(R.string.warning));
                    generalDialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), "");

                    Intent intentLoginError = new Intent(TAG);
                    intentLoginError.putExtra("failed", true);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentLoginError);
                } else if (response.getSystemDebugMessage().equals(context.getString(R.string.error_password))) {
                    GeneralDialogFragment generalDialogFragment = GeneralDialogFragment.newInstance(context,
                            context.getString(R.string.error_password), context.getString(R.string.warning));
                    generalDialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), "");

                    Intent intentPasswordError = new Intent(TAG);
                    intentPasswordError.putExtra("failed", true);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intentPasswordError);
                }
            }
        };
    }

    public static Response.ErrorListener errorLoginListener(final Context context, final String TAG) {
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