package com.applab.goodmorning.ContactUs.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * Created by user on 7/4/2016.
 */
public class ContactUsHolder extends RecyclerView.ViewHolder {
    private TextView mTxtBranchName, mTxtCompanyName, mTxtLocation, mTxtCareline, mTxtMobileNo, mTxtTelNo, mTxtFaxNo, mTxtUrl, mTxtEmail, mTxtOperationHour;
    private LinearLayout mBtnGoogleMap, mBtnWaze;

    public ContactUsHolder(View itemView) {
        super(itemView);
        mTxtBranchName = (TextView) itemView.findViewById(R.id.txtBranchName);
        mTxtCompanyName = (TextView) itemView.findViewById(R.id.txtCompanyName);
        mTxtLocation = (TextView) itemView.findViewById(R.id.txtLocation);
        mTxtCareline = (TextView) itemView.findViewById(R.id.txtCareline);
        mTxtMobileNo = (TextView) itemView.findViewById(R.id.txtMobileNo);
        mTxtTelNo = (TextView) itemView.findViewById(R.id.txtTelNo);
        mTxtFaxNo = (TextView) itemView.findViewById(R.id.txtFaxNo);
        mTxtUrl = (TextView) itemView.findViewById(R.id.txtUrl);
        mTxtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
        mTxtOperationHour = (TextView) itemView.findViewById(R.id.txtOperationHour);
        mBtnGoogleMap = (LinearLayout) itemView.findViewById(R.id.btnGoogleMap);
        mBtnWaze = (LinearLayout) itemView.findViewById(R.id.btnWaze);
    }

    public TextView getmTxtBranchName() {
        return mTxtBranchName;
    }

    public TextView getmTxtCompanyName() {
        return mTxtCompanyName;
    }

    public TextView getmTxtLocation() {
        return mTxtLocation;
    }

    public TextView getmTxtCareline() {
        return mTxtCareline;
    }

    public TextView getmTxtMobileNo() {
        return mTxtMobileNo;
    }

    public TextView getmTxtTelNo() {
        return mTxtTelNo;
    }

    public TextView getmTxtFaxNo() {
        return mTxtFaxNo;
    }

    public TextView getmTxtUrl() {
        return mTxtUrl;
    }

    public TextView getmTxtEmail() {
        return mTxtEmail;
    }

    public TextView getmTxtOperationHour() {
        return mTxtOperationHour;
    }

    public LinearLayout getmBtnGoogleMap() {
        return mBtnGoogleMap;
    }

    public LinearLayout getmBtnWaze() {
        return mBtnWaze;
    }
}
