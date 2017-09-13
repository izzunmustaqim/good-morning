package com.applab.goodmorning.Event.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * Created by user on 4/3/2016.
 */
public class ScheduleHolder extends RecyclerView.ViewHolder {
    private TextView mTxtDate, mTxtTitle, mTxtDesc;
    private ImageView mImgType;

    public ScheduleHolder(View itemView) {
        super(itemView);
        mTxtDate = (TextView)itemView.findViewById(R.id.txtDate);
        mTxtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
        mTxtDesc = (TextView)itemView.findViewById(R.id.txtDesc);
        mImgType = (ImageView)itemView.findViewById(R.id.imgType);
    }

    public TextView getmTxtDate() {
        return mTxtDate;
    }

    public TextView getmTxtTitle() {
        return mTxtTitle;
    }

    public TextView getmTxtDesc() {
        return mTxtDesc;
    }

    public ImageView getmImgType() {
        return mImgType;
    }
}
