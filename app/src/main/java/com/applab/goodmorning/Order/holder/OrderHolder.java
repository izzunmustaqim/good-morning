package com.applab.goodmorning.Order.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * Created by user on 08-Mar-16.
 */
public class OrderHolder extends RecyclerView.ViewHolder {
    private TextView txtDate;
    private TextView txtOrderCode;
    private TextView txtStatus;

    public OrderHolder(View itemView) {
        super(itemView);
        txtDate = (TextView) itemView.findViewById(R.id.txtDate);
        txtOrderCode = (TextView) itemView.findViewById(R.id.txtOrderCode);
        txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public TextView getTxtOrderCode() {
        return txtOrderCode;
    }

    public TextView getTxtStatus() {
        return txtStatus;
    }
}
