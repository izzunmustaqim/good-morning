package com.applab.goodmorning.Checkout.webapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.applab.goodmorning.Account.database.CRUDHelper;
import com.applab.goodmorning.Account.model.Account;
import com.applab.goodmorning.Checkout.activity.CheckOutDoneActivity;
import com.applab.goodmorning.Checkout.model.Cart;
import com.applab.goodmorning.Checkout.model.CartItem;
import com.applab.goodmorning.Checkout.model.CheckOut;
import com.applab.goodmorning.Enquiry.activity.EnquiryActivity;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.model.TokenItem;
import com.applab.goodmorning.Products.model.Product;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.AppController;
import com.applab.goodmorning.Utilities.GsonRequest;
import com.applab.goodmorning.Utilities.Utilities;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.http.entity.ContentType;

import java.util.HashMap;

/**
 * Created by user on 14/4/2016.
 */
public class HttpHelper {
    public static void postOrder(Context context, String TAG, final CheckOut checkOut) {
        Utilities.sendLocalLock(true, context, TAG);
        final Token token = com.applab.goodmorning.Login.database.CRUDHelper.getToken(context);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token.getToken());
        GsonRequest<JsonObject> mGsonRequest = new GsonRequest<JsonObject>(
                Request.Method.POST,
                context.getString(R.string.base_url) + "Order/Post",
                JsonObject.class,
                headers,
                responseEventListListener(context, TAG),
                errorEventListListener(context, TAG)) {

            @Override
            public String getBodyContentType() {
                return ContentType.APPLICATION_JSON.toString();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                String json = gson.toJson(checkOut);
                return json.getBytes();
            }
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<JsonObject> responseEventListListener(final Context context, final String TAG) {
        return new Response.Listener<JsonObject>() {
            @Override
            public void onResponse(JsonObject response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("success", true);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                Utilities.sendLocalLock(false, context, null);
                Utilities.showError(context, "", context.getString(R.string.success_send));
                intent = new Intent(context, CheckOutDoneActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        };
    }

    public static Response.ErrorListener errorEventListListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent intent = new Intent(TAG);
                intent.putExtra("failed", true);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                Utilities.serverHandlingError(context, error);
                Utilities.sendLocalLock(false, context, null);
            }
        };
    }

    public static void postCart(Context context, String TAG, final int id, boolean isWelcome) {
        if (isWelcome) {
            Utilities.sendLocalLock(true, context, TAG);
        } else {
            Utilities.sendLocalLock(true, context, null);
        }
        final Token token = com.applab.goodmorning.Login.database.CRUDHelper.getToken(context);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token.getToken());
        GsonRequest<JsonObject> mGsonRequest = new GsonRequest<JsonObject>(
                Request.Method.POST,
                context.getString(R.string.base_url) + "Cart/Add",
                JsonObject.class,
                headers,
                responsePostProductListener(context, TAG, isWelcome),
                errorPostProductListenr(context, TAG, isWelcome)) {

            @Override
            public String getBodyContentType() {
                return ContentType.APPLICATION_JSON.toString();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                String json = "{\"ProductId\":" + id + "}";
                return json.getBytes();
            }
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<JsonObject> responsePostProductListener(final Context context, final String TAG, final boolean isWelcome) {
        return new Response.Listener<JsonObject>() {
            @Override
            public void onResponse(JsonObject response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("success", true);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                if (isWelcome) {
                    Utilities.sendLocalLock(false, context, TAG);
                } else {
                    Utilities.sendLocalLock(false, context, null);
                }
                Toast.makeText(context, context.getString(R.string.success_send), Toast.LENGTH_LONG).show();
            }
        };
    }

    public static Response.ErrorListener errorPostProductListenr(final Context context, final String TAG, final boolean isWelcome) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent intent = new Intent(TAG);
                intent.putExtra("failed", true);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                Utilities.serverHandlingError(context, error);
                if (isWelcome) {
                    Utilities.sendLocalLock(false, context, TAG);
                } else {
                    Utilities.sendLocalLock(false, context, null);
                }
            }
        };
    }

    public static void putCart(Context context, String TAG, final Product product, final TextView txtAmount, int oldCount, int newCount) {
        Utilities.sendLocalLock(true, context, null);
        final Token token = com.applab.goodmorning.Login.database.CRUDHelper.getToken(context);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token.getToken());
        GsonRequest<Product> mGsonRequest = new GsonRequest<Product>(
                Request.Method.PUT,
                context.getString(R.string.base_url) + "Cart/Update",
                Product.class,
                headers,
                responseCartListener(context, TAG, txtAmount, newCount),
                errorCartListener(context, TAG, txtAmount, oldCount)) {

            @Override
            public String getBodyContentType() {
                return ContentType.APPLICATION_JSON.toString();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                String json = gson.toJson(product);
                return json.getBytes();
            }
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<Product> responseCartListener(final Context context, final String TAG, final TextView txtAmount, final int newCount) {
        return new Response.Listener<Product>() {
            @Override
            public void onResponse(Product response) {
                Utilities.sendLocalLock(false, context, null);
                Utilities.showError(context, "", context.getString(R.string.success_send));
                HttpHelper.getCart(context, TAG, null, null);
                txtAmount.setText(String.valueOf(newCount));
            }
        };
    }

    public static Response.ErrorListener errorCartListener(final Context context, final String TAG, final TextView txtAmount, final int oldCount) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.sendLocalLock(false, context, null);
                Utilities.serverHandlingError(context, error);
                txtAmount.setText(String.valueOf(oldCount));
            }
        };
    }

    public static void getCart(Context context, String TAG, final String statusCode, final String countryId) {
        String url = context.getString(R.string.base_url) + "Cart/Get?stateCode=" + statusCode + "&countryId=" + countryId + "";
        if (countryId == null) {
            if (statusCode == null) {
                url = context.getString(R.string.base_url) + "Cart/Get?";
            }
        }
        final Token token = com.applab.goodmorning.Login.database.CRUDHelper.getToken(context);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token.getToken());
        GsonRequest<CartItem> mGsonRequest = new GsonRequest<CartItem>(
                Request.Method.GET,
                url,
                CartItem.class,
                headers,
                responseGetCartListener(context, TAG),
                errorGetCartListener(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<CartItem> responseGetCartListener(final Context context, final String TAG) {
        return new Response.Listener<CartItem>() {
            @Override
            public void onResponse(CartItem response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("CartItem", response);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        };
    }

    public static Response.ErrorListener errorGetCartListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.serverHandlingError(context, error);
            }
        };
    }

    public static void deleteCart(Context context, String TAG, final int id) {
        Utilities.sendLocalLock(true, context, null);
        final Token token = com.applab.goodmorning.Login.database.CRUDHelper.getToken(context);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token.getToken());
        GsonRequest<String> mGsonRequest = new GsonRequest<String>(
                Request.Method.DELETE,
                context.getString(R.string.base_url) + "Cart/Delete?productId=" + id + "",
                String.class,
                headers,
                responseDeleteProductListener(context, TAG),
                errorDeleteProductListenr(context, TAG)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<String> responseDeleteProductListener(final Context context, final String TAG) {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Utilities.sendLocalLock(false, context, null);
                Utilities.showError(context, "", context.getString(R.string.success_send));
                Account account = CRUDHelper.getAccount(context);
                HttpHelper.getCart(context, TAG, account.getDefStateCode(), account.getDefCountryId().toString());
            }
        };
    }

    public static Response.ErrorListener errorDeleteProductListenr(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.sendLocalLock(false, context, null);
                Utilities.serverHandlingError(context, error);
            }
        };
    }

    public static void getCount(Context context, String TAG, final View actionShop) {
        final Token token = com.applab.goodmorning.Login.database.CRUDHelper.getToken(context);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token.getToken());
        GsonRequest<CartItem> mGsonRequest = new GsonRequest<CartItem>(
                Request.Method.GET,
                context.getString(R.string.base_url) + "Cart/Count",
                CartItem.class,
                headers,
                responseGetCountListener(context, TAG, actionShop),
                errorGetCountListener(context, TAG, actionShop)) {
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<CartItem> responseGetCountListener(final Context context, final String TAG, final View actionShop) {
        return new Response.Listener<CartItem>() {
            @Override
            public void onResponse(CartItem response) {
                Intent intent = new Intent(TAG);
                intent.putExtra("CartItem", response);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                TextView txtNumber = (TextView) actionShop.findViewById(R.id.hotlist_hot);
                if (response.getCarts().get(0).getCount() == 0) {
                    txtNumber.setVisibility(View.GONE);
                } else if (response.getCarts().get(0).getCount() > 9) {
                    txtNumber.setVisibility(View.VISIBLE);
                    txtNumber.setText("+9");
                } else {
                    txtNumber.setVisibility(View.VISIBLE);
                    txtNumber.setText(String.valueOf(response.getCarts().get(0).getCount()));
                }
            }
        };
    }

    public static Response.ErrorListener errorGetCountListener(final Context context, final String TAG, final View actionShop) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.serverHandlingError(context, error);
                TextView txtNumber = (TextView) actionShop.findViewById(R.id.hotlist_hot);
                txtNumber.setVisibility(View.GONE);
            }
        };
    }

    public static void postPromotionCode(Context context, String TAG, final String promotionCode) {
        Utilities.sendLocalLock(true, context, TAG);
        final Token token = com.applab.goodmorning.Login.database.CRUDHelper.getToken(context);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token.getToken());
        GsonRequest<TokenItem> mGsonRequest = new GsonRequest<TokenItem>(
                Request.Method.POST,
                context.getString(R.string.base_url) + "Code/Apply",
                TokenItem.class,
                headers,
                responsePostPromotionCodeListener(context, TAG),
                errorPostPromotionCodeListener(context, TAG)) {

            @Override
            public String getBodyContentType() {
                return ContentType.APPLICATION_JSON.toString();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                String json = "{\"PromotionCode\":" + promotionCode + "}";
                return json.getBytes();
            }
        };
        mGsonRequest.setRetryPolicy(new DefaultRetryPolicy(Utilities.TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(mGsonRequest, TAG);
    }

    public static Response.Listener<TokenItem> responsePostPromotionCodeListener(final Context context, final String TAG) {
        return new Response.Listener<TokenItem>() {
            @Override
            public void onResponse(TokenItem response) {
                if (response.getSystemCode() == 200) {
                    getCart(context, TAG, null, null);
                    Utilities.showError(context, "", context.getString(R.string.success_send));
                } else {
                    Utilities.showError(context, "", context.getString(R.string.promotion_code_error));
                }
                Utilities.sendLocalLock(false, context, TAG);
            }
        };
    }

    public static Response.ErrorListener errorPostPromotionCodeListener(final Context context, final String TAG) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.serverHandlingError(context, error);
                Utilities.sendLocalLock(false, context, TAG);
                Utilities.showError(context, "", context.getString(R.string.promotion_code_error));
            }
        };
    }

}
