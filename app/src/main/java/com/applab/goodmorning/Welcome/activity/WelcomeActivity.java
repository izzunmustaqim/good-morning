package com.applab.goodmorning.Welcome.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.Account.activity.AccountDetailsActivity;
import com.applab.goodmorning.Checkout.activity.CartActivity;
import com.applab.goodmorning.Login.activity.LoginActivity;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.provider.TokenProvider;
import com.applab.goodmorning.Menu.database.MenuDBHelper;
import com.applab.goodmorning.Menu.fragment.NavigationDrawerFragment;
import com.applab.goodmorning.News.webapi.HttpHelper;
import com.applab.goodmorning.Order.activity.OrderHistoryActivity;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.activity.RegisterActivity;
import com.applab.goodmorning.Utilities.Utilities;

public class WelcomeActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private NavigationDrawerFragment mDrawerFragment;
    private View mSideMenu;
    private RelativeLayout mRl;
    private LinearLayout mBtnSignUp;
    private LinearLayout mBtnAccount;
    private LinearLayout mBtnHistory;
    private LinearLayout mBtnLogOut;
    private LinearLayout mBtnRegister;
    private TextView mTxtProfileName;
    private RelativeLayout mRlFade;
    private ProgressBar mProgressBar;
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    private View mActionShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_welcome));

        mRlFade = (RelativeLayout) findViewById(R.id.fadeProgressBarRL);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        Utilities.setFadeProgressBarVisibility(false, mRlFade, mProgressBar);

        mDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerFragment.setSelectedPosition(0);

        mSideMenu = (View) findViewById(R.id.sideMenu);
        mBtnSignUp = (LinearLayout) mSideMenu.findViewById(R.id.btnSignIn);
        mBtnAccount = (LinearLayout) mSideMenu.findViewById(R.id.btnAccount);
        mBtnHistory = (LinearLayout) mSideMenu.findViewById(R.id.btnHistory);
        mBtnLogOut = (LinearLayout) mSideMenu.findViewById(R.id.btnSignOut);
        mBtnRegister = (LinearLayout) mSideMenu.findViewById(R.id.btnRegister);
        mTxtProfileName = (TextView) mSideMenu.findViewById(R.id.txtProfileName);
        mRl = (RelativeLayout) findViewById(R.id.fadeRL);
        Utilities.setSideMenuOnClickListener(this, mTxtProfileName, mBtnSignUp, mBtnRegister, mBtnAccount, mBtnHistory, mBtnLogOut, mSideMenu, mRl);

        mToolbar.setNavigationIcon(R.mipmap.menu);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter iff = new IntentFilter(TAG);
        setDelay(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, iff);
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
        Utilities.checkToCloseSideMenuForOnPause(this, mSideMenu, mRl, mDrawerFragment);
        MenuDBHelper.updateMenu(WelcomeActivity.this);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                if (intent.getBooleanExtra("isLock", false)) {
                    Utilities.setFadeProgressBarVisibility(true, mRlFade, mProgressBar);
                } else {
                    Utilities.refreshActionShop(WelcomeActivity.this, mActionShop);
                    Utilities.setFadeProgressBarVisibility(false, mRlFade, mProgressBar);
                }
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        mActionShop = (View) menu.findItem(R.id.action_shop).getActionView();
        Utilities.setAddCartOnClickListener(WelcomeActivity.this, mActionShop);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_menu) {
            Utilities.setSideMenuVisible(true, mSideMenu, mRl);
            return true;
        } else {
            return mDrawerFragment.mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
        }
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mDrawerFragment.setOpenCloseDrawer();
        }
    };

    @Override
    public void onBackPressed() {
        Utilities.checkToCloseSideMenu(this, mSideMenu, mRl, mDrawerFragment);
    }
}
