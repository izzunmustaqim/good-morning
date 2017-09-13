package com.applab.goodmorning.Account.webapi;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.Account.database.CRUDHelper;
import com.applab.goodmorning.Account.model.Account;
import com.applab.goodmorning.Account.model.ItemList;
import com.applab.goodmorning.Account.provider.AccountProvider;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;

import java.util.HashMap;

/**
 * Created by user on 28-Mar-16.
 */
public class HttpHelper {
    public static void getMyProfileApi(Context context, String TAG) {
        final Token token = com.applab.goodmorning.Login.database.CRUDHelper.getToken(context);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token.getToken());
        if (token != null) {
            if (token.getToken() != null) {
                GsonRequest<ItemList> mGsonRequest = new GsonRequest<ItemList>(
                        Request.Method.GET,
                        context.getString(R.string.base_url) + "Account/MyProfile",
                        ItemList.class,
                        headers,
                        responseAccountListener(context, TAG),
                        errorAccountListener(context, TAG)) {
                };
                mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
            }
        }
    }

    public static Response.Listener<ItemList> responseAccountListener(final Context context, final String TAG) {
        return new Response.Listener<ItemList>() {
            @Override
            public void onResponse(ItemList response) {
                Log.i(TAG, "Success Retrieved Data - " + response);
                context.getContentResolver().delete(AccountProvider.CONTENT_URI, null, null);
                CRUDHelper.insertAccount(context, response, TAG);
            }
        };
    }

    public static Response.ErrorListener errorAccountListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.serverHandlingError(context, error);
            }
        };
    }
}
