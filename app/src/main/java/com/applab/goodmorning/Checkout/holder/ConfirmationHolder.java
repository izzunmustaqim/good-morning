package com.applab.goodmorning.Checkout.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * Created by user on 4/4/2016.
 */
public class ConfirmationHolder extends RecyclerView.ViewHolder {
    private TextView mTxtName, mTxtPrice, mTxtQuantity, mTxtDesc;

    public ConfirmationHolder(View itemView) {
        super(itemView);
        mTxtName = (TextView) itemView.findViewById(R.id.txtName);
        mTxtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
        mTxtQuantity = (TextView) itemView.findViewById(R.id.txtQuantity);
        mTxtDesc = (TextView) itemView.findViewById(R.id.txtDesc);
    }

    public TextView getmTxtQuantity() {
        return mTxtQuantity;
    }

    public TextView getmTxtDesc() {
        return mTxtDesc;
    }

    public TextView getmTxtName() {
        return mTxtName;
    }

    public TextView getmTxtPrice() {
        return mTxtPrice;
    }
}

