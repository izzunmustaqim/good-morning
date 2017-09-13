package com.applab.goodmorning.Enquiry.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.Checkout.activity.CartActivity;
import com.applab.goodmorning.ContactUs.adapter.ContactUsPagerAdapter;
import com.applab.goodmorning.Enquiry.adapter.EnquiryPagerAdapter;
import com.applab.goodmorning.Menu.fragment.NavigationDrawerFragment;
import com.applab.goodmorning.R;
import com.applab.goodmorning.SlidingTabs.SlidingTabLayout;
import com.applab.goodmorning.Utilities.Utilities;

public class EnquiryActivity extends AppCompatActivity {
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
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private int mPage = 0;
    private RelativeLayout mRlFadeForProgressBar;
    private ProgressBar mProgressBar;
    private String TAG = EnquiryActivity.class.getSimpleName();
    private View mActionShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_enquiry));
        mToolbar.setFitsSystemWindows(false);
        mToolbar.setPadding(0, 0, 0, 0);

        mSideMenu = (View) findViewById(R.id.sideMenu);
        mBtnSignUp = (LinearLayout) mSideMenu.findViewById(R.id.btnSignIn);
        mBtnAccount = (LinearLayout) mSideMenu.findViewById(R.id.btnAccount);
        mBtnHistory = (LinearLayout) mSideMenu.findViewById(R.id.btnHistory);
        mBtnLogOut = (LinearLayout) mSideMenu.findViewById(R.id.btnSignOut);
        mBtnRegister = (LinearLayout) mSideMenu.findViewById(R.id.btnRegister);
        mTxtProfileName = (TextView) mSideMenu.findViewById(R.id.txtProfileName);
        mRl = (RelativeLayout) findViewById(R.id.fadeRL);
        Utilities.setSideMenuOnClickListener(this, mTxtProfileName, mBtnSignUp, mBtnRegister, mBtnAccount, mBtnHistory, mBtnLogOut, mSideMenu, mRl);

        mDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerFragment.setSelectedPosition(8);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(2);
        mPager.setAdapter(new EnquiryPagerAdapter(getSupportFragmentManager(), this));
        mPager.addOnPageChangeListener(mPagerOnPageChangeListener);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText, R.id.tabNumber, R.id.tabIcon);
        mTabs.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        mTabs.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.color_primary));
        mTabs.setViewPager(mPager);
        mPager.setCurrentItem(mPage);

        mToolbar.setNavigationIcon(R.mipmap.menu);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);

        mRlFadeForProgressBar = (RelativeLayout) findViewById(R.id.fadeRLForProgressBar);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        Utilities.setFadeProgressBarVisibility(false, mRlFadeForProgressBar, mProgressBar);
    }

    private ViewPager.OnPageChangeListener mPagerOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mPage = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        mActionShop = (View) menu.findItem(R.id.action_shop).getActionView();
        Utilities.setAddCartOnClickListener(EnquiryActivity.this, mActionShop);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utilities.checkToCloseSideMenu(this, mSideMenu, mRl, mDrawerFragment);
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
    public void onResume() {
        super.onResume();
        setDelay(this);
        IntentFilter iff = new IntentFilter(TAG);
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

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                if (intent.getBooleanExtra("isLock", false)) {
                    Utilities.setFadeProgressBarVisibility(true, mRlFadeForProgressBar, mProgressBar);
                } else {
                    Utilities.setFadeProgressBarVisibility(false, mRlFadeForProgressBar, mProgressBar);
                }
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        Utilities.checkToCloseSideMenuForOnPause(this, mSideMenu, mRl, mDrawerFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }
}
