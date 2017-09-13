package com.applab.goodmorning.News.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.applab.goodmorning.R;

/**
 * Created by user on 24-Mar-16.
 */
public class NewsDetailsHolder extends RecyclerView.ViewHolder {
    private ImageView imgProducts;

    public NewsDetailsHolder(View itemView) {
        super(itemView);
        imgProducts = (ImageView) itemView.findViewById(R.id.imgProducts);
    }

    public ImageView getImgProducts() {
        return imgProducts;
    }
}
