package com.applab.goodmorning.Promotions.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applab.goodmorning.Promotions.activity.PromotionsActivity;
import com.applab.goodmorning.Promotions.model.Promotion;
import com.applab.goodmorning.R;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 15/4/2016
 * -- Description: PromotionDialog .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class PromotionDialog extends DialogFragment {
    private TextView mTxtTitle;
    private ImageView mBtnCancel;
    private LinearLayout mBtnPDF;
    private LinearLayout mBtnPic;
    private TextView mTxtMessage;
    private static AlertDialog.Builder builder;
    private static Context mContext;
    private String TAG = PromotionDialog.class.getSimpleName();
    private Promotion mPromotion;

    public static PromotionDialog newInstance(final Context context, Promotion promotion) {
        PromotionDialog frag = new PromotionDialog();
        Bundle args = new Bundle();
        args.putParcelable("Promotion", promotion);
        frag.setArguments(args);
        mContext = context;
        builder = new AlertDialog.Builder(context);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mPromotion = getArguments().getParcelable("Promotion");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.window_promotion, null);

        mTxtTitle = (TextView) v.findViewById(R.id.txtTitle);
        mBtnCancel = (ImageView) v.findViewById(R.id.btnCancel);
        mBtnPDF = (LinearLayout) v.findViewById(R.id.btnPDF);
        mBtnPic = (LinearLayout) v.findViewById(R.id.btnPic);
        mTxtMessage = (TextView) v.findViewById(R.id.txtMessage);

        mBtnCancel.setOnClickListener(btnCancelOnClickListener);
        mBtnPDF.setOnClickListener(btnPDFOnClickListener);
        mBtnPic.setOnClickListener(btnPicOnClickListener);
        mTxtTitle.setText(getResources().getString(R.string.title_selection));
        mTxtMessage.setText(getResources().getString(R.string.promotion_messages));

        //Builder dialog
        builder.setView(v);
        Dialog d = builder.create();
        d.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return d;
    }

    private View.OnClickListener btnCancelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PromotionDialog.this.getDialog().cancel();
        }
    };

    private View.OnClickListener btnPDFOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PromotionsActivity.class.getSimpleName());
            intent.putExtra("isPDF", true);
            intent.putExtra("Promotion", mPromotion);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            PromotionDialog.this.getDialog().cancel();
        }
    };

    private View.OnClickListener btnPicOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PromotionsActivity.class.getSimpleName());
            intent.putExtra("isPic", true);
            intent.putExtra("Promotion", mPromotion);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            PromotionDialog.this.getDialog().cancel();
        }
    };
}
