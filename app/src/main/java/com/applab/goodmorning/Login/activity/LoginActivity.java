package com.applab.goodmorning.Login.activity;

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

import com.applab.goodmorning.ForgetPassword.activity.ForgotPasswordActivity;
import com.applab.goodmorning.ForgetPassword.model.ForgetPassword;
import com.applab.goodmorning.Login.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.activity.RegisterActivity;
import com.applab.goodmorning.Utilities.DBHelper;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Utilities.dialog.GeneralDialogFragment;
import com.applab.goodmorning.Welcome.activity.WelcomeActivity;

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
 * 1    Edwin Cheong
 * 2
 */

public class LoginActivity extends AppCompatActivity {

    private RelativeLayout mBtnForgetPassword;
    private RelativeLayout mBtnBack;
    private Button mBtnSignUp;
    private Button mBtnLogin;
    private EditText mEditEmail;
    private EditText mEditPassword;
    private String TAG = LoginActivity.class.getSimpleName();
    private ProgressBar mProgressBar;
    private RelativeLayout mRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtnForgetPassword = (RelativeLayout) findViewById(R.id.btnForgetPassword);
        mBtnBack = (RelativeLayout) findViewById(R.id.btnBack);
        mBtnSignUp = (Button) findViewById(R.id.btnSignUp);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mEditEmail = (EditText) findViewById(R.id.editEmail);
        mEditPassword = (EditText) findViewById(R.id.editPassword);
        mRl = (RelativeLayout) findViewById(R.id.fadeRL);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mBtnForgetPassword.setOnClickListener(mBtnForgetPasswordOnClickListener);
        mBtnBack.setOnClickListener(mBtnBackOnClickListener);
        mBtnSignUp.setOnClickListener(mBtnSignUpPasswordOnClickListener);
        mBtnLogin.setOnClickListener(mBtnLoginOnClickListener);
    }

    private View.OnClickListener mBtnBackOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener mBtnForgetPasswordOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener mBtnSignUpPasswordOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    };

    private boolean checkValidation() {
        String message = null;
        if (mEditEmail.getText().toString().length() == 0) {
            message = getString(R.string.username_email_error);
        } else if (mEditPassword.getText().toString().length() == 0) {
            message = getString(R.string.password_errors);
        }
        if (message != null) {
            GeneralDialogFragment generalDialogFragment = GeneralDialogFragment.newInstance(this, message, getString(R.string.warning));
            generalDialogFragment.show(getSupportFragmentManager(), "");

        }
        return message == null;
    }

    private View.OnClickListener mBtnLoginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String email = mEditEmail.getText().toString().trim();
            final String password = mEditPassword.getText().toString().trim();
            if (checkValidation()) {
                HttpHelper.loginApi(LoginActivity.this, TAG, email, password);
                Utilities.hideKeyboard(LoginActivity.this);
                Utilities.setFadeProgressBarVisibility(true, mRl, mProgressBar);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbHelper = new DBHelper(this);
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(broadcastReceiver, iff);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getBaseContext()).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                if (intent.getBooleanExtra("failed", true)) {
                    Utilities.setFadeProgressBarVisibility(false, mRl, mProgressBar);
                }
            }
        }
    };
}