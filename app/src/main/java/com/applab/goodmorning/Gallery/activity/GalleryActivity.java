package com.applab.goodmorning.Gallery.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.Account.activity.AccountDetailsActivity;
import com.applab.goodmorning.Checkout.activity.CartActivity;
import com.applab.goodmorning.Gallery.adapter.GalleryPagerAdapter;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.provider.TokenProvider;
import com.applab.goodmorning.Menu.fragment.NavigationDrawerFragment;
import com.applab.goodmorning.Order.activity.OrderHistoryActivity;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.activity.RegisterActivity;
import com.applab.goodmorning.SlidingTabs.SlidingTabLayout;
import com.applab.goodmorning.Utilities.Utilities;

public class GalleryActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView mTxtTitle;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private int mPage = 0;
    private NavigationDrawerFragment mDrawerFragment;
    private String TAG = GalleryActivity.class.getSimpleName();
    private View mSideMenu;
    private RelativeLayout mRl;
    private LinearLayout mBtnSignUp;
    private LinearLayout mBtnAccount;
    private LinearLayout mBtnHistory;
    private LinearLayout mBtnLogOut;
    private LinearLayout mBtnRegister;
    private TextView mTxtProfileName;
    private boolean mIsPhoto = false;
    private View mActionShop;
    private int mLoaderId = 2172;
    private Token mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtTitle.setText(getBaseContext().getResources().getString(R.string.title_activity_gallery));

        mDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(false);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(1);
        mPager.setAdapter(new GalleryPagerAdapter(getSupportFragmentManager(), this));
        mPager.addOnPageChangeListener(mPagerOnPageChangeListener);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText, R.id.tabNumber, R.id.tabIcon);
        mTabs.setBackgroundColor(ContextCompat.getColor(this, R.color.color_white));
        mTabs.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.color_primary));
        setPage(getIntent());
        mTabs.setViewPager(mPager);

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

    public void setPage(Intent intent) {
        if (intent.getIntExtra("ARG", 0) == 2) {
            mPage = 0;
            mPager.setCurrentItem(mPage);
            mDrawerFragment.setSelectedPosition(2);
        } else if (intent.getIntExtra("ARG", 0) == 3) {
            mPage = 1;
            mPager.setCurrentItem(mPage);
            mDrawerFragment.setSelectedPosition(3);
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                if (intent.getBooleanExtra("isPhoto", false)) {
                    mIsPhoto = intent.getBooleanExtra("isPhoto", false);
                } else {
                    setPage(intent);
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        setDelay(this);
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(broadcastReceiver, iff);
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
        LocalBroadcastManager.getInstance(getBaseContext()).unregisterReceiver(broadcastReceiver);
        Utilities.checkToCloseSideMenuForOnPause(this, mSideMenu, mRl, mDrawerFragment);
    }

    @Override
    public void onBackPressed() {
        if (mPage == 0) {
            if (mIsPhoto) {
                Intent intent = new Intent(TAG);
                intent.putExtra("isBack", true);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                mIsPhoto = false;
            } else {
                Utilities.checkToCloseSideMenu(this, mSideMenu, mRl, mDrawerFragment);
            }
        } else {
            Utilities.checkToCloseSideMenu(this, mSideMenu, mRl, mDrawerFragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        mActionShop = (View) menu.findItem(R.id.action_shop).getActionView();
        Utilities.setAddCartOnClickListener(GalleryActivity.this, mActionShop);
        return true;
    }

    private ViewPager.OnPageChangeListener mPagerOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            mPage = position;
            if(mPage==0){
                mDrawerFragment.setSelectedPosition(2);
            }else{
                mDrawerFragment.setSelectedPosition(3);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

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
}