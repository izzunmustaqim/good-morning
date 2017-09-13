package com.applab.goodmorning.ChangePassword.webapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.ChangePassword.model.ChangePassword;
import com.applab.goodmorning.ChangePassword.model.Password;
import com.applab.goodmorning.Login.database.CRUDHelper;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.provider.TokenProvider;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Welcome.activity.WelcomeActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.http.entity.ContentType;

import java.util.HashMap;
import java.util.Map;

import eu.janmuller.android.simplecropimage.Util;

/**
 * Created by user on 28/3/2016.
 */
public class HttpHelper {
    public static void putChangePassword(Context context, String TAG, final Password password) {
        Map<String, String> header = new HashMap<String, String>();
        Token token = CRUDHelper.getToken(context);
        header.put("Authorization", token.getToken());
        GsonRequest<ChangePassword> mGsonRequest = new GsonRequest<ChangePassword>(
                Request.Method.PUT,
                context.getString(R.string.base_url) + "Account/ChangePassword",
                ChangePassword.class,
                header,
                responseChangePasswordListener(context, TAG),
                errorChangePasswordListenr(context, TAG)) {

            @Override
            public String getBodyContentType() {
                return ContentType.APPLICATION_JSON.toString();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                String json = gson.toJson(password);
                return json.getBytes();
            }
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<ChangePassword> responseChangePasswordListener(final Context context, final String TAG) {
        return new Response.Listener<ChangePassword>() {
            @Override
            public void onResponse(ChangePassword response) {
                int errorCode = 400;
                if (response.getSystemCode() != errorCode) {
                    context.getContentResolver().delete(TokenProvider.CONTENT_URI, null, null);
                    Intent intent = new Intent(context, WelcomeActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                } else {
                    Utilities.showError(context, "", response.getSystemMessage());
                    Intent intent = new Intent(TAG);
                    intent.putExtra("failed", true);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            }
        };
    }

    public static Response.ErrorListener errorChangePasswordListenr(final Context context, final String TAG) {
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
