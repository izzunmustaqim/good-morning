package com.applab.goodmorning.Order.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * Created by user on 17-Mar-16.
 */
public class OrderDetailsHolder extends RecyclerView.ViewHolder {
    private TextView txtProductTitle, txtProductQuantity, txtProductPrice, txtProductWeight;

    public OrderDetailsHolder(View itemView) {
        super(itemView);
        txtProductTitle = (TextView) itemView.findViewById(R.id.txtProductTitle);
        txtProductQuantity = (TextView) itemView.findViewById(R.id.txtProductQuantity);
        txtProductPrice = (TextView) itemView.findViewById(R.id.txtProductPrice);
        txtProductWeight = (TextView) itemView.findViewById(R.id.txtProductWeight);
    }

    public TextView getTxtProductTitle() {
        return txtProductTitle;
    }

    public TextView getTxtProductQuantity() {
        return txtProductQuantity;
    }

    public TextView getTxtProductPrice() {
        return txtProductPrice;
    }

    public TextView getTxtProductWeight() {
        return txtProductWeight;
    }
}
