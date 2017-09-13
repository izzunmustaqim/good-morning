package com.applab.goodmorning.Checkout.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 9/3/2016
 * -- Description: CartHolder .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class CartHolder extends RecyclerView.ViewHolder {
    private TextView txtProductName;
    private TextView txtProductPrice;
    private TextView txtProductWeight;
    private ImageView imgProduct;
    private TextView txtProductCount;
    private RelativeLayout btnMinus;
    private RelativeLayout btnPlus;
    private TextView txtAmount;

    public CartHolder(View itemView) {
        super(itemView);
        txtProductName = (TextView) itemView.findViewById(R.id.txtProductName);
        txtProductPrice = (TextView) itemView.findViewById(R.id.txtProductPrice);
        txtProductWeight = (TextView) itemView.findViewById(R.id.txtProductDetails);
        imgProduct = (ImageView) itemView.findViewById(R.id.imgProducts);
        txtAmount = (TextView) itemView.findViewById(R.id.txtAmount);
        btnMinus = (RelativeLayout) itemView.findViewById(R.id.btnMinus);
        btnPlus = (RelativeLayout) itemView.findViewById(R.id.btnPlus);
    }

    public TextView getTxtProductName() {
        return txtProductName;
    }

    public TextView getTxtProductPrice() {
        return txtProductPrice;
    }

    public TextView getTxtProductWeight() {
        return txtProductWeight;
    }

    public ImageView getImgProducts() {
        return imgProduct;
    }

    public TextView getTxtProductCount() {
        return txtProductCount;
    }

    public RelativeLayout getBtnMinus() {
        return btnMinus;
    }

    public RelativeLayout getBtnPlus() {
        return btnPlus;
    }

    public TextView getTxtAmount() {
        return txtAmount;
    }
}

