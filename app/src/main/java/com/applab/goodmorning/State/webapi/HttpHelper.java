package com.applab.goodmorning.State.webapi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.R;
import com.applab.goodmorning.State.database.CRUDHelper;
import com.applab.goodmorning.State.model.StateItem;
import com.applab.goodmorning.State.provider.StateProvider;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;

/**
 * Created by user on 31-Mar-16.
 */
public class HttpHelper {
    public static void getStateList(Context context, String TAG, int countryId) {
        GsonRequest<StateItem> mGsonRequest = new GsonRequest<StateItem>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "State/List?countryId=" + countryId,
                StateItem.class,
                null,
                responseStateListener(context, TAG, countryId),
                errorStateListenr(context, TAG)
        ) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<StateItem> responseStateListener(final Context context, final String TAG, final int countryId) {
        return new Response.Listener<StateItem>() {
            @Override
            public void onResponse(StateItem response) {
                CRUDHelper.insertState(context, TAG, response, countryId);
            }
        };
    }

    public static Response.ErrorListener errorStateListenr(final Context context, final String TAG) {
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
