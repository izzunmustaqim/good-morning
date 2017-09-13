package com.applab.goodmorning.Welcome.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.applab.goodmorning.R;

/**
 * Created by user on 14-Mar-16.
 */
public class SlidingImageDotsHolder extends RecyclerView.ViewHolder {
    private ImageView mImgDot;

    public SlidingImageDotsHolder(View itemView) {
        super(itemView);
        mImgDot = (ImageView) itemView.findViewById(R.id.imgDot);
    }

    public ImageView getmImgDot() {
        return mImgDot;
    }
}
