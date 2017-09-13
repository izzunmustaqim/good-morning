package com.applab.goodmorning.Order.webapi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.Account.model.ItemList;
import com.applab.goodmorning.Account.provider.AccountProvider;
import com.applab.goodmorning.Login.database.CRUDHelper;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Order.model.OrderItem;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 8/4/2016.
 */
public class HttpHelper {
    public static void getOrderHistory(final Context context, final String TAG) {
        final Token token = CRUDHelper.getToken(context);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token.getToken());
        if (token != null) {
            GsonRequest<OrderItem> mGsonRequest = new GsonRequest<OrderItem>(
                    Request.Method.GET,
                    context.getString(R.string.base_url) + "Order/History",
                    OrderItem.class,
                    headers,
                    responseOrderListener(context, TAG),
                    errorNewsDetailsListenr(context, TAG)
            ) {

            };

            mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
        }
    }

    public static Response.Listener<OrderItem> responseOrderListener(final Context context, final String TAG) {
        return new Response.Listener<OrderItem>() {
            @Override
            public void onResponse(OrderItem response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("Order", response);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorNewsDetailsListenr(final Context context, final String TAG) {
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
