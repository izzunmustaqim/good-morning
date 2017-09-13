package com.applab.goodmorning.ForgetPassword.webapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.ForgetPassword.model.Email;
import com.applab.goodmorning.ForgetPassword.model.ForgetPassword;
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

/**
 * Created by User on 6/4/2016.
 */
public class HttpHelper {
    public static void putForgetPassword(final Context context, final String TAG, final Email email) {
        GsonRequest<ForgetPassword> mGsonRequest = new GsonRequest<ForgetPassword>(
                Request.Method.PUT,
                context.getString(R.string.base_url) + "Account/ForgotPassword",
                ForgetPassword.class,
                null,
                responseForgetPassword(context, TAG),
                errorForgerPassword(context, TAG)) {

            @Override
            public String getBodyContentType() {
                return ContentType.APPLICATION_JSON.toString();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                String json = gson.toJson(email);
                return json.getBytes();
            }
        };

        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);

    }

    public static Response.Listener<ForgetPassword> responseForgetPassword(final Context context, final String TAG) {
        return new Response.Listener<ForgetPassword>() {
            @Override
            public void onResponse(ForgetPassword response) {
                int systemCode = 300;
                if (response.getSystemCode() != systemCode) {
                    Utilities.showError(context, Utilities.CODE_SUCCESS, Utilities.ForgotPassword);
                    Intent intent = new Intent(context, WelcomeActivity.class);
                    context.startActivity(intent);
                    Utilities.showError(context, "", context.getString(R.string.success_send));
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

    public static Response.ErrorListener errorForgerPassword(final Context context, final String TAG) {
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
