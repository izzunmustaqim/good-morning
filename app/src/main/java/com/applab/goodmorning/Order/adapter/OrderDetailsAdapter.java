package com.applab.goodmorning.Order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.Order.holder.OrderDetailsHolder;
import com.applab.goodmorning.Order.model.Order;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Welcome.model.Products;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 9/3/2016
 * -- Description: OrderDetailsAdapter .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsHolder> {
    private Context mContext;
    private Order mOrder;
    private LayoutInflater mInflater;

    public OrderDetailsAdapter(Context context, Order orders) {
        mContext = context;
        mOrder = orders;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public OrderDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.custom_order_details_row, parent, false);
        return new OrderDetailsHolder(v);
    }

    @Override
    public void onBindViewHolder(OrderDetailsHolder holder, int position) {
        holder.getTxtProductTitle().setText(mOrder.getOrderHistoryProducts().get(position).getProductName());
        holder.getTxtProductQuantity().setText("x " + mOrder.getOrderHistoryProducts().get(position).getQuantity());
        holder.getTxtProductPrice().setText("RM " + String.valueOf(mOrder.getOrderHistoryProducts().get(position).getPrice()));
        holder.getTxtProductWeight().setText(mOrder.getOrderHistoryProducts().get(position).getProductSubtitle());
    }

    @Override
    public int getItemCount() {
        return mOrder.getOrderHistoryProducts().size();
    }
}

