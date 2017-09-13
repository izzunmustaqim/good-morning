package com.applab.goodmorning.News.webapi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.News.model.News;
import com.applab.goodmorning.News.model.NewsItem;
import com.applab.goodmorning.R;

import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;

import java.util.ArrayList;

import eu.janmuller.android.simplecropimage.Util;

/**
 * Created by User on 4/4/2016.
 */
public class HttpHelper {
    public static void getNews(final Context context, final String TAG) {
        GsonRequest<NewsItem> mGsonRequest = new GsonRequest<NewsItem>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "News/List?countryId=" + Utilities.getCountryID(context) + "&newsType=" + TAG + "&PageNo=1&NoPerPage=1",
                NewsItem.class,
                null,
                responseNewsItemListener(context, TAG),
                errorNewsItemListenr(context, TAG)
        ) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<NewsItem> responseNewsItemListener(final Context context, final String TAG) {
        return new Response.Listener<NewsItem>() {
            @Override
            public void onResponse(NewsItem response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("News", response);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorNewsItemListenr(final Context context, final String TAG) {
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

    public static void getNewsDetails(final Context context, final String TAG, final int newsId) {
        String url = context.getString(R.string.base_url) + "News/Single?newsId=" + newsId;
        GsonRequest<NewsItem> mGsonRequest = new GsonRequest<NewsItem>(
                Request.Method.GET,
                url,
                NewsItem.class,
                null,
                responseNewsDetailsListener(context, TAG),
                errorNewsDetailsListenr(context, TAG)
        ) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<NewsItem> responseNewsDetailsListener(final Context context, final String TAG) {
        return new Response.Listener<NewsItem>() {
            @Override
            public void onResponse(NewsItem response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("NewsDetailsBroadcast", response);
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
