package com.applab.goodmorning.Checkout.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.applab.goodmorning.R;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 3/3/2016
 * -- Description: DialogFragment .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class ConfirmationDialogFragment extends DialogFragment {
    private Button btn_home;
    private static Context mContext;
    private static AlertDialog.Builder builder;

    public static ConfirmationDialogFragment newInstance(final Context context) {
        ConfirmationDialogFragment frag = new ConfirmationDialogFragment();
        Bundle args = new Bundle();
        builder = new AlertDialog.Builder(context);
        mContext = context;
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.window_order, null);

        btn_home = (Button) v.findViewById(R.id.btnHome);
        btn_home.setOnClickListener(btnCancelOnClickListener);

        builder.setView(v);
        Dialog d = builder.create();
        d.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return d;
    }

    private View.OnClickListener btnCancelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ConfirmationDialogFragment.this.getDialog().cancel();
        }
    };

}
