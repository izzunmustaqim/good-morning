package com.applab.goodmorning.Promotions.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 11/4/2016
 * -- Description: PromotionHolder .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class PromotionHolder extends RecyclerView.ViewHolder {
    private TextView tvPromotion;
    private TextView tvPromotionDate;
    private TextView tvPromotionDetails;
    private ImageView ivProduct;

    public PromotionHolder(View itemView){
        super(itemView);
        tvPromotion = (TextView) itemView.findViewById(R.id.txtPromotions);
        tvPromotionDate = (TextView) itemView.findViewById(R.id.txtPromotionsDate);
        tvPromotionDetails = (TextView) itemView.findViewById(R.id.txtPromotionDetails);
        ivProduct = (ImageView) itemView.findViewById(R.id.imgProducts);
    }

    public TextView getTvPromotion() {
        return tvPromotion;
    }

    public TextView getTvPromotionDate() {
        return tvPromotionDate;
    }

    public TextView getTvPromotionDetails() {
        return tvPromotionDetails;
    }

    public ImageView getIvProduct() {
        return ivProduct;
    }
}
