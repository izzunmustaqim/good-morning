package com.applab.goodmorning.Settings.acitivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.Account.activity.AccountDetailsActivity;
import com.applab.goodmorning.Checkout.activity.CartActivity;
import com.applab.goodmorning.Language.activity.LanguageActivity;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.provider.TokenProvider;
import com.applab.goodmorning.Menu.fragment.NavigationDrawerFragment;
import com.applab.goodmorning.Order.activity.OrderHistoryActivity;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.activity.RegisterActivity;
import com.applab.goodmorning.SelectCountry.activity.SelectCountryActivity;
import com.applab.goodmorning.Settings.adapter.SettingsAdapter;
import com.applab.goodmorning.Settings.model.Settings;
import com.applab.goodmorning.Utilities.ItemClickSupport;
import com.applab.goodmorning.Utilities.Utilities;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private NavigationDrawerFragment mDrawerFragment;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerview;
    private SettingsAdapter mAdapter;
    private ArrayList<Settings> mSettings = new ArrayList<>();
    private View mSideMenu;
    private RelativeLayout mRl;
    private LinearLayout mBtnSignUp;
    private LinearLayout mBtnAccount;
    private LinearLayout mBtnHistory;
    private LinearLayout mBtnLogOut;
    private TextView mTxtProfileName;
    private LinearLayout mBtnRegister;

    private int mLoaderId = 3357;
    private Cursor mCursor;
    private static final String TAG = SettingsActivity.class.getSimpleName();
    private Token mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        insertRow();

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_settings));

        mSideMenu = (View) findViewById(R.id.sideMenu);
        mBtnSignUp = (LinearLayout) mSideMenu.findViewById(R.id.btnSignIn);
        mBtnAccount = (LinearLayout) mSideMenu.findViewById(R.id.btnAccount);
        mBtnHistory = (LinearLayout) mSideMenu.findViewById(R.id.btnHistory);
        mBtnLogOut = (LinearLayout) mSideMenu.findViewById(R.id.btnSignOut);
        mBtnRegister = (LinearLayout) mSideMenu.findViewById(R.id.btnRegister);
        mTxtProfileName = (TextView) mSideMenu.findViewById(R.id.txtProfileName);
        mRl = (RelativeLayout) findViewById(R.id.fadeRL);
        Utilities.setSideMenuOnClickListener(this, mTxtProfileName, mBtnSignUp, mBtnRegister, mBtnAccount, mBtnHistory, mBtnLogOut, mSideMenu, mRl);

        mRecyclerview = (RecyclerView) findViewById(R.id.myRecyclerView);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(SettingsActivity.this);
        mAdapter = new SettingsAdapter(SettingsActivity.this, mSettings);

        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(mLayoutManager);

        mDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(false);

        mDrawerFragment.setSelectedPosition(10);

        ItemClickSupport.addTo(mRecyclerview).setOnItemClickListener(mItemClickListener);

        mToolbar.setNavigationIcon(R.mipmap.menu);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        View actionShop = (View) menu.findItem(R.id.action_shop).getActionView();
        Utilities.setAddCartOnClickListener(SettingsActivity.this, actionShop);
        return true;
    }

    public void insertRow() {
        String[] selectedItems = {
                Utilities.getCountry(SettingsActivity.this)
                , Utilities.getLanguage(this)};
        String[] selection = {getString(R.string.select_your_country)
                , getString(R.string.select_your_language)};

        for (int i = 0; i < 2; i++) {
            Settings settings = new Settings();
            settings.setItemSelected(selectedItems[i]);
            settings.setSelection(selection[i]);
            mSettings.add(settings);
        }
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

    private ItemClickSupport.OnItemClickListener mItemClickListener = new ItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
            if (position == 1) {
                Intent intent = new Intent(SettingsActivity.this, LanguageActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(SettingsActivity.this, SelectCountryActivity.class);
                startActivity(intent);
            }
        }
    };
}
