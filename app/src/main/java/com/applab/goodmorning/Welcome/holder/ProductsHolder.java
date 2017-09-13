package com.applab.goodmorning.Welcome.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * Created by user on 04-Mar-16.
 */
public class ProductsHolder extends RecyclerView.ViewHolder {
    private CardView cardView;
    private ImageView imgProducts;
    private TextView txtProductName;
    private TextView txtProductWeight;
    private TextView txtProductPrice;
    private RelativeLayout btnReadMore;
    private RelativeLayout btnAddToCart;

    public ProductsHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        imgProducts = (ImageView) itemView.findViewById(R.id.imgProducts);
        txtProductName = (TextView) itemView.findViewById(R.id.txtProductName);
        txtProductWeight = (TextView) itemView.findViewById(R.id.txtProductDetails);
        txtProductPrice = (TextView) itemView.findViewById(R.id.txtProductPrice);
        btnReadMore = (RelativeLayout) itemView.findViewById(R.id.btnReadMore);
        btnAddToCart = (RelativeLayout) itemView.findViewById(R.id.btnAddToCart);
    }

    public CardView getCardView() {
        return cardView;
    }

    public ImageView getImgProducts() {
        return imgProducts;
    }

    public TextView getTxtProductName() {
        return txtProductName;
    }

    public TextView getTxtProductWeight() {
        return txtProductWeight;
    }

    public TextView getTxtProductPrice() {
        return txtProductPrice;
    }

    public RelativeLayout getBtnReadMore() {
        return btnReadMore;
    }

    public RelativeLayout getBtnAddToCart() {
        return btnAddToCart;
    }
}
