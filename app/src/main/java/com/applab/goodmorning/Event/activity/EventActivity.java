package com.applab.goodmorning.Event.activity;

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
import com.applab.goodmorning.Event.adapter.EventPagerAdapter;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.provider.TokenProvider;
import com.applab.goodmorning.Menu.fragment.NavigationDrawerFragment;
import com.applab.goodmorning.Order.activity.OrderHistoryActivity;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.activity.RegisterActivity;
import com.applab.goodmorning.SlidingTabs.SlidingTabLayout;
import com.applab.goodmorning.Utilities.Utilities;

public class EventActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView mTxtTitle;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private int mPage = 0;
    private NavigationDrawerFragment mDrawerFragment;
    private int mToolbarHeight = 100;
    private View mSideMenu;
    private RelativeLayout mRl;
    private TextView mTxtProfileName;
    private LinearLayout mBtnSignUp;
    private LinearLayout mBtnAccount;
    private LinearLayout mBtnHistory;
    private LinearLayout mBtnLogOut;
    private LinearLayout mBtnRegister;
    private View mActionShop;
    private boolean mIsSlide = false;
    private String TAG = EventActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mToolbar = (Toolbar) findViewById(R.id.appBar);
        setSupportActionBar(mToolbar);
        mTxtTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtTitle.setText(getBaseContext().getResources().getString(R.string.title_activity_events));
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(1);
        mPager.setAdapter(new EventPagerAdapter(getSupportFragmentManager(), this));
        mPager.addOnPageChangeListener(mPagerOnPageChangeListener);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText, R.id.tabNumber, R.id.tabIcon);
        mTabs.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        mTabs.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.color_primary));
        mTabs.setViewPager(mPager);
        mPager.setCurrentItem(mPage);

        mSideMenu = (View) findViewById(R.id.sideMenu);
        mBtnSignUp = (LinearLayout) mSideMenu.findViewById(R.id.btnSignIn);
        mBtnAccount = (LinearLayout) mSideMenu.findViewById(R.id.btnAccount);
        mBtnHistory = (LinearLayout) mSideMenu.findViewById(R.id.btnHistory);
        mBtnLogOut = (LinearLayout) mSideMenu.findViewById(R.id.btnSignOut);
        mBtnRegister = (LinearLayout) mSideMenu.findViewById(R.id.btnRegister);
        mTxtProfileName = (TextView) mSideMenu.findViewById(R.id.txtProfileName);
        mRl = (RelativeLayout) findViewById(R.id.fadeRL);

        mDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.setSelectedPosition(6);
        mDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(false);

        mToolbar.setNavigationIcon(R.mipmap.menu);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mDrawerFragment.setOpenCloseDrawer();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        mActionShop = (View) menu.findItem(R.id.action_shop).getActionView();
        Utilities.setAddCartOnClickListener(EventActivity.this, mActionShop);
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

    @Override
    protected void onPause() {
        super.onPause();
        Utilities.checkToCloseSideMenuForOnPause(this, mSideMenu, mRl, mDrawerFragment);
    }

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
    public void onBackPressed() {
        if (!mIsSlide) {
            Utilities.checkToCloseSideMenu(this, mSideMenu, mRl, mDrawerFragment);
        } else {
            Intent intent = new Intent(TAG);
            intent.putExtra("isSlide", false);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }

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

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                mIsSlide = intent.getBooleanExtra("isSlide", false);
            }
        }
    };
}
