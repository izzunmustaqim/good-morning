package com.applab.goodmorning.ForgetPassword.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.applab.goodmorning.ForgetPassword.model.Email;
import com.applab.goodmorning.ForgetPassword.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Utilities.dialog.GeneralDialogFragment;
import com.google.android.gms.gcm.GcmNetworkManager;

import eu.janmuller.android.simplecropimage.Util;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 4/3/2016
 * -- Description: AssigneeList .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button mBtnCancel;
    private Button mBtnSubmit;
    private EditText mEtEmail;
    private String TAG = ForgotPasswordActivity.class.getSimpleName();
    private ProgressBar mProgressBar;
    private RelativeLayout mRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mBtnCancel = (Button) findViewById(R.id.btnCancel);
        mBtnSubmit = (Button) findViewById(R.id.btnSubmit);
        mEtEmail = (EditText) findViewById(R.id.etEmail);
        mRl = (RelativeLayout) findViewById(R.id.fadeRL);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mBtnCancel.setOnClickListener(mBtnCancelOnClickListener);
        mBtnSubmit.setOnClickListener(mBtnSubmitOnClickListener);
    }

    private boolean checkValidation() {
        String message = null;
        if (mEtEmail.getText().toString().length() == 0) {
            message = getString(R.string.email_error);
        } else if (!Utilities.isValidEmail(mEtEmail.getText().toString())) {
            message = getString(R.string.email_not_valid);
        }
        if (message != null) {
            GeneralDialogFragment generalDialogFragment = GeneralDialogFragment.newInstance(this, message, getString(R.string.warning));
            generalDialogFragment.show(getSupportFragmentManager(), "");

        }
        return message == null;
    }

    private View.OnClickListener mBtnCancelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener mBtnSubmitOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkValidation()) {
                Utilities.setFadeProgressBarVisibility(true, mRl, mProgressBar);
                Email mEmail = new Email();
                mEmail.setEmail(mEtEmail.getText().toString());
                HttpHelper.putForgetPassword(ForgotPasswordActivity.this, TAG, mEmail);
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getBaseContext()).unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(broadcastReceiver, iff);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                if (intent.getBooleanExtra("failed", false)) {
                    Utilities.setFadeProgressBarVisibility(false, mRl, mProgressBar);
                }
            }
        }
    };
}
