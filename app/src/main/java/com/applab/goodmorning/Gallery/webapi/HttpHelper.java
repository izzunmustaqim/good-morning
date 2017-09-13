package com.applab.goodmorning.Gallery.webapi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.Gallery.model.Album;
import com.applab.goodmorning.Gallery.model.AlbumItems;
import com.applab.goodmorning.Gallery.model.PhotoItems;
import com.applab.goodmorning.Gallery.model.VideoItems;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;

import java.util.HashMap;

/**
 * Created by user on 30/3/2016.
 */
public class HttpHelper {
    public static void getAlbum(Context context, String TAG) {
        GsonRequest<AlbumItems> mGsonRequest = new GsonRequest<AlbumItems>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Gallery/Album?countryId=" + Utilities.getCountryID(context) + "",
                AlbumItems.class,
                null,
                responseAlbumItemsListener(context, TAG),
                errorAlbumItemsListener(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<AlbumItems> responseAlbumItemsListener(final Context context, final String TAG) {
        return new Response.Listener<AlbumItems>() {
            @Override
            public void onResponse(AlbumItems response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("Albums", response);
                intent.putExtra("isAlbum", true);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorAlbumItemsListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.serverHandlingError(context, error);
            }
        };
    }

    public static void getPhoto(Context context, String TAG, Album album) {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "A+ZsKSXPIn1id++Uh1vg1g==");
        GsonRequest<PhotoItems> mGsonRequest = new GsonRequest<PhotoItems>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Gallery/Album/Single?albumId=" + album.getAlbumId().toString(),
                PhotoItems.class,
                headers,
                responsePhotoItemsListener(context, TAG),
                errorPhotoItemsListener(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<PhotoItems> responsePhotoItemsListener(final Context context, final String TAG) {
        return new Response.Listener<PhotoItems>() {
            @Override
            public void onResponse(PhotoItems response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("Photos", response);
                intent.putExtra("isPhoto", true);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorPhotoItemsListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.serverHandlingError(context, error);
            }
        };
    }

    public static void getVideo(Context context, String TAG) {
        GsonRequest<VideoItems> mGsonRequest = new GsonRequest<VideoItems>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Gallery/Video?countryId=" + Utilities.getCountryID(context) + "",
                VideoItems.class,
                null,
                responseVideoItemsListener(context, TAG),
                errorVideoItemsListener(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<VideoItems> responseVideoItemsListener(final Context context, final String TAG) {
        return new Response.Listener<VideoItems>() {
            @Override
            public void onResponse(VideoItems response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("Videos", response);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorVideoItemsListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.serverHandlingError(context, error);
            }
        };
    }
}
