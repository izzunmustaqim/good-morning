package com.applab.goodmorning.Gallery.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.applab.goodmorning.R;

/**
 * Created by user on 10-Mar-16.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgPhotos;
    private ImageView imgPlay;
    private RelativeLayout mRl;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imgPhotos = (ImageView) itemView.findViewById(R.id.imgPhotos);
        imgPlay = (ImageView) itemView.findViewById(R.id.imgPlay);
        mRl = (RelativeLayout) itemView.findViewById(R.id.rl);
    }

    public RelativeLayout getmRl() {
        return mRl;
    }

    public ImageView getImgPhotos() {
        return imgPhotos;
    }

    public ImageView getImgPlay() {
        return imgPlay;
    }
}
