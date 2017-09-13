package com.applab.goodmorning.Products.webapi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Products.activity.ProductActivity;
import com.applab.goodmorning.Products.database.CRUDHelper;
import com.applab.goodmorning.Products.model.ProductItems;
import com.applab.goodmorning.Products.provider.ProductProvider;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;

import org.apache.http.entity.ContentType;

import java.util.HashMap;

/**
 * Created by user on 29-Mar-16.
 */
public class HttpHelper {
    public static void getProductListing(Context context, String TAG) {
        GsonRequest<ProductItems> mGsonRequest = new GsonRequest<ProductItems>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Product/List?countryId=" + Utilities.getCountryID(context) + "",
                ProductItems.class,
                null,
                responseProductListener(context, TAG),
                errorProductListenr(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<ProductItems> responseProductListener(final Context context, final String TAG) {
        return new Response.Listener<ProductItems>() {
            @Override
            public void onResponse(ProductItems response) {
                if (TAG.equals(ProductActivity.class.getSimpleName())) {
                    context.getContentResolver().delete(ProductProvider.CONTENT_URI, null, null);
                    CRUDHelper.insertProducts(response, context, TAG);
                } else {
                    Intent intent = new Intent(TAG);
                    intent.putExtra("Products", response);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            }
        };
    }

    public static Response.ErrorListener errorProductListenr(final Context context, final String TAG) {
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

    //GET Product Details
    public static void getProductDetails(int productID, Context context, String TAG) {
        GsonRequest<ProductItems> mGsonRequest = new GsonRequest<ProductItems>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Product/Single?productId=" + productID,
                ProductItems.class,
                null,
                responseProductDetailsListener(context, TAG),
                errorProductDetailsListener(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<ProductItems> responseProductDetailsListener(final Context context, final String TAG) {
        return new Response.Listener<ProductItems>() {
            @Override
            public void onResponse(ProductItems response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("Product", response.getItems().get(0));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorProductDetailsListener(final Context context, final String TAG) {
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

    //GET Product Promo
    public static void getProductPromo(Context context, String TAG) {
        GsonRequest<ProductItems> mGsonRequest = new GsonRequest<ProductItems>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Product/Promo?countryId=" + Utilities.getCountryID(context),
                ProductItems.class,
                null,
                responseProductPromoListener(context, TAG),
                errorProductPromoListener(context, TAG)
        ) {
        };

        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<ProductItems> responseProductPromoListener(final Context context, final String TAG) {
        return new Response.Listener<ProductItems>() {
            @Override
            public void onResponse(ProductItems response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("ProductPromo", response);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorProductPromoListener(final Context context, final String TAG) {
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
