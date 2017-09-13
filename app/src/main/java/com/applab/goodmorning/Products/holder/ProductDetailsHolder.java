package com.applab.goodmorning.Products.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.applab.goodmorning.R;

/**
 * Created by user on 29-Mar-16.
 */
public class ProductDetailsHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    private View mView;

    public ProductDetailsHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imgProducts);
        mView = (View) itemView.findViewById(R.id.view);
    }

    public View getmView() {
        return mView;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
