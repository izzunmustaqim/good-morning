package com.applab.goodmorning.Checkout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.Checkout.holder.ConfirmationHolder;
import com.applab.goodmorning.Checkout.model.CartItem;
import com.applab.goodmorning.Products.model.Product;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Welcome.model.Products;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 5/3/2016
 * -- Description: ConfirmationAdapter .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class ConfirmationAdapter extends RecyclerView.Adapter<ConfirmationHolder> {
    private Context mContext;
    private CartItem mCartItem;
    private LayoutInflater mInflater;

    public ConfirmationAdapter(Context context, CartItem cartItem) {
        mContext = context;
        mCartItem = cartItem;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ConfirmationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.custom_confirmation, parent, false);
        return new ConfirmationHolder(v);
    }

    @Override
    public void onBindViewHolder(ConfirmationHolder holder, int position) {
        Product product = mCartItem.getCarts().get(0).getProducts().get(position);
        holder.getmTxtDesc().setText(product.getProductSubTitle());
        holder.getmTxtName().setText(product.getProductTitle());
        holder.getmTxtPrice().setText(product.getPrice());
        holder.getmTxtQuantity().setText("X" + String.valueOf(product.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return mCartItem == null ? 0 : mCartItem.getCarts().get(0).getProducts().size();
    }

    public void swapCartItem(CartItem cartItem) {
        this.mCartItem = cartItem;
        this.notifyDataSetChanged();
    }
}
