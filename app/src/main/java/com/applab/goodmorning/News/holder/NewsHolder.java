package com.applab.goodmorning.News.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * Created by user on 09-Mar-16.
 */
public class NewsHolder extends RecyclerView.ViewHolder {
    private TextView txtDate;
    private TextView txtTitle;
    private TextView txtDescription;
    private ImageView imgProducts;
    private TextView txtBottomDate;

    public NewsHolder(View itemView) {
        super(itemView);
        txtDate = (TextView) itemView.findViewById(R.id.txtDate);
        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        txtDescription = (TextView) itemView.findViewById(R.id.txtDetails);
        imgProducts = (ImageView) itemView.findViewById(R.id.imgProducts);
        txtBottomDate = (TextView) itemView.findViewById(R.id.txtBottomDate);
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public TextView getTxtDescription() {
        return txtDescription;
    }

    public ImageView getImgProducts() {
        return imgProducts;
    }

    public TextView getTxtBottomDate() {
        return txtBottomDate;
    }
}
