package com.applab.goodmorning.Account.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.Account.model.Account;
import com.applab.goodmorning.Account.provider.AccountProvider;
import com.applab.goodmorning.Account.webapi.HttpHelper;
import com.applab.goodmorning.ChangePassword.activity.ChangePasswordActivity;
import com.applab.goodmorning.Checkout.activity.CartActivity;
import com.applab.goodmorning.EditDetails.activity.EditDetailsActivity;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.provider.TokenProvider;
import com.applab.goodmorning.Order.activity.OrderHistoryActivity;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.activity.RegisterActivity;
import com.applab.goodmorning.Utilities.Utilities;

/**
 * -- =============================================
 * -- Author     : Edwin Cheong Leong Teck
 * -- Create date: 11/3/2016
 * -- Description: Details Of Accounts
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class AccountDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private RelativeLayout mBtnChangePassword;
    private RelativeLayout mBtnEdit;

    private View mSideMenu;
    private RelativeLayout mRl;
    private LinearLayout mBtnSignUp;
    private LinearLayout mBtnAccount;
    private LinearLayout mBtnHistory;
    private LinearLayout mBtnLogOut;
    private TextView mTxtProfileName;
    private View mActionShop;
    private TextView mTxtUserName;
    private TextView mTxtEmail;
    private TextView mTxtFirstName;
    private TextView mTxtLastName;
    private TextView mTxtPhone;
    private TextView mTxtAddress;
    private TextView mTxtCity;
    private TextView mTxtState;
    private TextView mTxtPostCode;
    private TextView mTxtCountry;
    private int mLoaderId = 3231;
    private int mLoaderId2 = 2122;
    private String TAG = AccountDetailsActivity.class.getSimpleName();
    private Account mAccount;
    private Token mToken;
    private LinearLayout mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_my_account));

        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);

        mBtnChangePassword = (RelativeLayout) findViewById(R.id.btnChangePassword);
        mBtnChangePassword.setOnClickListener(mBtnChangePasswordClickListener);

        mBtnEdit = (RelativeLayout) findViewById(R.id.btnEdit);
        mBtnEdit.setOnClickListener(mBtnEditOnClickListener);

        mTxtUserName = (TextView) findViewById(R.id.txtUserName);
        mTxtEmail = (TextView) findViewById(R.id.txtEmail);
        mTxtFirstName = (TextView) findViewById(R.id.txtFirstName);
        mTxtLastName = (TextView) findViewById(R.id.txtLastName);
        mTxtPhone = (TextView) findViewById(R.id.txtPhone);
        mTxtAddress = (TextView) findViewById(R.id.txtAddress);
        mTxtCity = (TextView) findViewById(R.id.txtCity);
        mTxtState = (TextView) findViewById(R.id.txtState);
        mTxtPostCode = (TextView) findViewById(R.id.txtPostCode);
        mTxtCountry = (TextView) findViewById(R.id.txtCountry);

        mSideMenu = (View) findViewById(R.id.sideMenu);
        mBtnSignUp = (LinearLayout) mSideMenu.findViewById(R.id.btnSignIn);
        mBtnAccount = (LinearLayout) mSideMenu.findViewById(R.id.btnAccount);
        mBtnHistory = (LinearLayout) mSideMenu.findViewById(R.id.btnHistory);
        mBtnLogOut = (LinearLayout) mSideMenu.findViewById(R.id.btnSignOut);
        mBtnRegister = (LinearLayout) mSideMenu.findViewById(R.id.btnRegister);
        mTxtProfileName = (TextView) mSideMenu.findViewById(R.id.txtProfileName);
        mRl = (RelativeLayout) findViewById(R.id.fadeRL);
        Utilities.setSideMenuOnClickListener(this, mTxtProfileName, mBtnSignUp, mBtnRegister, mBtnAccount, mBtnHistory, mBtnLogOut, mSideMenu, mRl);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDelay(this);
        HttpHelper.getMyProfileApi(AccountDetailsActivity.this, TAG);
        getSupportLoaderManager().initLoader(mLoaderId, null, this);
    }

    private void setDelay(final Context context) {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                Utilities.refreshActionShop(context, mActionShop);
            }
        };
        handler.postDelayed(r, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Utilities.checkSideMenuOnPause(this, mSideMenu, mRl);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (mLoaderId == id) {
            return new CursorLoader(AccountDetailsActivity.this, AccountProvider.CONTENT_URI, null, null, null, null);
        }
        return null;
    }

    private void setData(Cursor data) {
        mAccount = Account.getAccount(data, 0);
        mTxtUserName.setText(mAccount.getUsername());
        mTxtEmail.setText(mAccount.getEmail());
        mTxtFirstName.setText(mAccount.getFirstName());
        mTxtLastName.setText(mAccount.getLastName());
        mTxtPhone.setText(mAccount.getContactNo());
        mTxtAddress.setText(mAccount.getAddress());
        mTxtCity.setText(mAccount.getCity());
        mTxtState.setText(mAccount.getState());
        mTxtPostCode.setText(mAccount.getZipcode());
        mTxtCountry.setText(mAccount.getCountry());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (loader.getId() == mLoaderId) {
            if (data != null) {
                if (data.getCount() > 0) {
                    setData(data);
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_menu) {
            Utilities.setSideMenuVisible(true, mSideMenu, mRl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        mActionShop = (View) menu.findItem(R.id.action_shop).getActionView();
        Utilities.setAddCartOnClickListener(AccountDetailsActivity.this, mActionShop);
        return true;
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener mActionShopOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AccountDetailsActivity.this, CartActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener mBtnChangePasswordClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AccountDetailsActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener mBtnEditOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AccountDetailsActivity.this, EditDetailsActivity.class);
            intent.putExtra("Details", mAccount);
            startActivity(intent);
        }
    };
}
