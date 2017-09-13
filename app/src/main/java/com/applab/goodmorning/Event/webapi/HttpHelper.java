package com.applab.goodmorning.Event.webapi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.Event.model.Schedule;
import com.applab.goodmorning.Event.model.ScheduleItems;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by user on 1/4/2016.
 */
public class HttpHelper {
    public static void getEventList(Context context, String TAG, String startDate, String endDate) {
        GsonRequest<ScheduleItems> mGsonRequest = new GsonRequest<ScheduleItems>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Event/List/Schedule?countryId=" + Utilities.getCountryID(context) + "&fromDate="
                        + startDate + "&toDate=" + endDate + "",
                ScheduleItems.class,
                null,
                responseEventListListener(context, TAG),
                errorEventListListener(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<ScheduleItems> responseEventListListener(final Context context, final String TAG) {
        return new Response.Listener<ScheduleItems>() {
            @Override
            public void onResponse(ScheduleItems response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("Schedules", response);
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

    public static void getEvent(Context context, String TAG, Schedule schedule) {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "A+ZsKSXPIn1id++Uh1vg1g==");
        GsonRequest<ScheduleItems> mGsonRequest = new GsonRequest<ScheduleItems>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Event/Single?eventId=" + schedule.getEventId().toString(),
                ScheduleItems.class,
                headers,
                responseEventListener(context, TAG),
                errorEventListener(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<ScheduleItems> responseEventListener(final Context context, final String TAG) {
        return new Response.Listener<ScheduleItems>() {
            @Override
            public void onResponse(ScheduleItems response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("Schedule", response);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorEventListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.serverHandlingError(context, error);
            }
        };
    }

    public static void getMonth(Context context, String TAG, String startDate, String endDates) {
        GsonRequest<ScheduleItems> mGsonRequest = new GsonRequest<ScheduleItems>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Event/List/Month?countryId=" + Utilities.getCountryID(context) + "&fromDate=" + startDate + "&toDate=" + endDates,
                ScheduleItems.class,
                null,
                responseMonthListener(context, TAG),
                errorMonthListener(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<ScheduleItems> responseMonthListener(final Context context, final String TAG) {
        return new Response.Listener<ScheduleItems>() {
            @Override
            public void onResponse(ScheduleItems response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("Schedules", response);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorMonthListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.serverHandlingError(context, error);
            }
        };
    }
}
