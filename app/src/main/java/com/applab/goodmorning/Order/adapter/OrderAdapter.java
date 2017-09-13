package com.applab.goodmorning.Order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.Order.holder.OrderHolder;
import com.applab.goodmorning.Order.model.Order;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;

import java.util.ArrayList;

/**
 * Created by user on 08-Mar-16.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Order> mOrder = new ArrayList<>();

    public OrderAdapter(Context context, ArrayList<Order> orders) {
        mContext = context;
        mOrder = orders;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.custom_order_list, parent, false);
        return new OrderHolder(v);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        holder.getTxtDate().setText(Utilities.setCalendarDate("yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", mOrder.get(position).getCreateDate()));
        holder.getTxtOrderCode().setText(mOrder.get(position).getOrderId());
        holder.getTxtStatus().setText(mOrder.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return mOrder == null ? 0 : mOrder.size();
    }

    public void swapOrder(ArrayList<Order> mOrder) {
        this.mOrder = mOrder;
        this.notifyDataSetChanged();
    }
}
