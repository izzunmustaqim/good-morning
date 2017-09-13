package com.applab.goodmorning.Menu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * -- =============================================
 * -- Author     : Edwin Cheong
 * -- Create date: 3/3/2016
 * -- Description: Holding the menu
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class MenuHolder extends RecyclerView.ViewHolder {
    private TextView mTxtTitle;
    private ImageView mImgIcon;
    private RelativeLayout relativeLayout;
    private ImageView imgNext;

    public MenuHolder(View itemView) {
        super(itemView);
        mTxtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        mImgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl);
        imgNext = (ImageView) itemView.findViewById(R.id.imgNext);
    }

    public TextView getmTxtTitle() {
        return mTxtTitle;
    }

    public ImageView getmImgIcon() {
        return mImgIcon;
    }

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    public ImageView getImgNext() {
        return imgNext;
    }
}
