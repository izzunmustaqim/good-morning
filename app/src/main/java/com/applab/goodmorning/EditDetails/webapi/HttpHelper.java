package com.applab.goodmorning.EditDetails.webapi;

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
import com.applab.goodmorning.Account.activity.AccountDetailsActivity;
import com.applab.goodmorning.Login.database.CRUDHelper;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.provider.TokenProvider;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.model.Register;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.http.entity.ContentType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 30/3/2016.
 */
public class HttpHelper {
    public static void editDetails(Context context, String TAG, final Register register) {
        Map<String, String> header = new HashMap<String, String>();
        Token token = CRUDHelper.getToken(context);
        header.put("Authorization", token.getToken());
        GsonRequest<JsonObject> mGsonRequest = new GsonRequest<JsonObject>(
                Request.Method.PUT,
                context.getString(R.string.base_url) + "Account/EditProfile",
                JsonObject.class,
                header,
                responseEditProfileListener(context, TAG),
                errorEditProfileListener(context, TAG)) {

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

    public static Response.Listener<JsonObject> responseEditProfileListener(final Context context, final String TAG) {
        return new Response.Listener<JsonObject>() {
            @Override
            public void onResponse(JsonObject response) {
                Intent intent = new Intent(context, AccountDetailsActivity.class);
                context.startActivity(intent);
                //Toast.makeText(context,"Done Edit",Toast.LENGTH_LONG).show();
                ((Activity) context).finish();
            }
        };
    }

    public static Response.ErrorListener errorEditProfileListener(final Context context, final String TAG) {
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