package com.applab.goodmorning.Products.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * Created by user on 07-Mar-16.
 */
public class ProductHolder extends RecyclerView.ViewHolder {
    private TextView txtProductName;
    private TextView txtProductPrice;
    private TextView txtProductWeight;
    private ImageView imgProduct;
    private RelativeLayout btnReadMore;
    private RelativeLayout btnAddToCart;

    public ProductHolder(View itemView) {
        super(itemView);
        txtProductName = (TextView) itemView.findViewById(R.id.txtProductName);
        txtProductPrice = (TextView) itemView.findViewById(R.id.txtProductPrice);
        txtProductWeight = (TextView) itemView.findViewById(R.id.txtProductWeight);
        imgProduct = (ImageView) itemView.findViewById(R.id.imgProducts);
        btnReadMore = (RelativeLayout) itemView.findViewById(R.id.btnReadMore);
        btnAddToCart = (RelativeLayout) itemView.findViewById(R.id.btnAddToCart);
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

    public ImageView getImgProduct() {
        return imgProduct;
    }

    public RelativeLayout getBtnReadMore() {
        return btnReadMore;
    }

    public RelativeLayout getBtnAddToCart() {
        return btnAddToCart;
    }
}
