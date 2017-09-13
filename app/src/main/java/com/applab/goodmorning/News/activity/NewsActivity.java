package com.applab.goodmorning.News.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.provider.TokenProvider;
import com.applab.goodmorning.Menu.database.MenuDBHelper;
import com.applab.goodmorning.Menu.fragment.NavigationDrawerFragment;
import com.applab.goodmorning.News.adapter.NewsManagerAdapter;
import com.applab.goodmorning.News.model.News;
import com.applab.goodmorning.Order.activity.OrderHistoryActivity;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.activity.RegisterActivity;
import com.applab.goodmorning.SlidingTabs.SlidingTabLayout;
import com.applab.goodmorning.Utilities.Utilities;

public class NewsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private NavigationDrawerFragment mDrawerFragment;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private String TAG = NewsActivity.class.getSimpleName();
    private int mPage = 0;
    private View mSideMenu;
    private RelativeLayout mRl;
    private LinearLayout mBtnSignUp;
    private LinearLayout mBtnAccount;
    private LinearLayout mBtnHistory;
    private LinearLayout mBtnLogOut;
    private LinearLayout mBtnRegister;
    private TextView mTxtProfileName;
    private View mActionShop;
    private Token mToken;
    private int mLoaderId = 1341;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_news));

        mSideMenu = (View) findViewById(R.id.sideMenu);
        mBtnSignUp = (LinearLayout) mSideMenu.findViewById(R.id.btnSignIn);
        mBtnAccount = (LinearLayout) mSideMenu.findViewById(R.id.btnAccount);
        mBtnHistory = (LinearLayout) mSideMenu.findViewById(R.id.btnHistory);
        mBtnLogOut = (LinearLayout) mSideMenu.findViewById(R.id.btnSignOut);
        mBtnRegister = (LinearLayout) mSideMenu.findViewById(R.id.btnRegister);
        mTxtProfileName = (TextView) mSideMenu.findViewById(R.id.txtProfileName);
        mRl = (RelativeLayout) findViewById(R.id.fadeRL);
        Utilities.setSideMenuOnClickListener(this, mTxtProfileName, mBtnSignUp, mBtnRegister, mBtnAccount, mBtnHistory, mBtnLogOut, mSideMenu, mRl);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(1);
        mPager.setAdapter(new NewsManagerAdapter(getSupportFragmentManager(), this));
        mPager.addOnPageChangeListener(mPagerOnPageChangeListener);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText, R.id.tabNumber, R.id.tabIcon);
        mTabs.setBackgroundColor(ContextCompat.getColor(this, R.color.color_white));
        mTabs.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.color_primary));
        mPage = getIntent().getIntExtra("ARG", mPage);
        mTabs.setViewPager(mPager);
        mPager.setCurrentItem(mPage);

        mDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(false);

        mDrawerFragment.setSelectedPosition(4);

        mToolbar.setNavigationIcon(R.mipmap.menu);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        mActionShop = (View) menu.findItem(R.id.action_shop).getActionView();
        Utilities.setAddCartOnClickListener(NewsActivity.this, mActionShop);
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
    protected void onResume() {
        super.onResume();
        setDelay(this);
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

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mDrawerFragment.setOpenCloseDrawer();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        Utilities.checkToCloseSideMenuForOnPause(this, mSideMenu, mRl, mDrawerFragment);
        MenuDBHelper.updateMenu(NewsActivity.this);
    }

    @Override
    public void onBackPressed() {
        Utilities.checkToCloseSideMenu(this, mSideMenu, mRl, mDrawerFragment);
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
}
