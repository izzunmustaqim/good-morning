package com.applab.goodmorning.ChangePassword.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.ChangePassword.model.Password;
import com.applab.goodmorning.ChangePassword.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Utilities.dialog.GeneralDialogFragment;

public class ChangePasswordActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private ImageButton mImg;
    private EditText mEditPassword;
    private EditText mEditNewPassword;
    private EditText mEditConfirmPassword;
    private RelativeLayout mBtnSubmit;
    private String TAG = ChangePasswordActivity.class.getSimpleName();
    private ProgressBar mProgressBar;
    private RelativeLayout mRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_change_password));

        mImg = (ImageButton) findViewById(R.id.img);
        mEditPassword = (EditText) findViewById(R.id.editPassword);
        mEditNewPassword = (EditText) findViewById(R.id.editNewPassword);
        mEditConfirmPassword = (EditText) findViewById(R.id.editConfirmPassword);
        mBtnSubmit = (RelativeLayout) findViewById(R.id.btnSubmit);
        mRl = (RelativeLayout) findViewById(R.id.fadeRL);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mImg.setVisibility(View.GONE);

        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);

        mBtnSubmit.setOnClickListener(mBtnSubmitOnClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private boolean checkValidation() {
        String message = null;
        if (mEditPassword.getText().toString().length() == 0) {
            message = getString(R.string.password_errors);
        } else if (mEditNewPassword.getText().toString().length() == 0) {
            message = getString(R.string.new_password_error);
        } else if (mEditConfirmPassword.getText().toString().length() == 0) {
            message = getString(R.string.confirm_password_errors);
        } else if (!mEditNewPassword.getText().toString().equals(mEditConfirmPassword.getText().toString())) {
            message = getString(R.string.new_password_confirm_not_match);
        }
        if (message != null) {
            GeneralDialogFragment generalDialogFragment = GeneralDialogFragment.newInstance(this, message, getString(R.string.warning));
            generalDialogFragment.show(getSupportFragmentManager(), "");

        }
        return message == null;
    }

    private View.OnClickListener mBtnSubmitOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String password = mEditPassword.getText().toString().trim();
            String newPassword = mEditNewPassword.getText().toString().trim();
            String confirmPassword = mEditConfirmPassword.getText().toString().trim();

            Password mPassword = new Password();
            mPassword.setOldPassword(Utilities.encode(password));
            mPassword.setNewPassword(Utilities.encode(newPassword));
            mPassword.setConfirmPassword(Utilities.encode(confirmPassword));

            if (checkValidation()) {
                Utilities.setFadeProgressBarVisibility(true, mRl, mProgressBar);
                HttpHelper.putChangePassword(ChangePasswordActivity.this, TAG, mPassword);
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
