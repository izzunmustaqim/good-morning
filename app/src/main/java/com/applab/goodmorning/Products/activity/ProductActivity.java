package com.applab.goodmorning.Products.activity;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.Account.activity.AccountDetailsActivity;
import com.applab.goodmorning.Checkout.activity.CartActivity;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Login.provider.TokenProvider;
import com.applab.goodmorning.Menu.fragment.NavigationDrawerFragment;
import com.applab.goodmorning.Order.activity.OrderHistoryActivity;
import com.applab.goodmorning.Products.adapter.ProductAdapter;
import com.applab.goodmorning.Products.provider.ProductProvider;
import com.applab.goodmorning.Products.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.activity.RegisterActivity;
import com.applab.goodmorning.Utilities.DBHelper;
import com.applab.goodmorning.Utilities.ItemClickSupport;
import com.applab.goodmorning.Utilities.Utilities;

public class ProductActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerview;
    private ProductAdapter mAdapter;
    private NavigationDrawerFragment mDrawerFragment;
    private View mSideMenu;
    private RelativeLayout mRl;
    private TextView mTxtProfileName;
    private LinearLayout mBtnSignUp;
    private LinearLayout mBtnAccount;
    private LinearLayout mBtnHistory;
    private LinearLayout mBtnLogOut;
    private LinearLayout mBtnRegister;

    private RelativeLayout mFadeProgressBarRL;
    private ProgressBar mProgressBarFade;

    private int mLoaderId = 7844;
    private Cursor mCursor;
    private String TAG = ProductActivity.class.getSimpleName();


    private EditText mEtSearch;
    private String mSelection;
    private String[] mSelectionArgs;

    private View mActionShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_products));
        mRecyclerview = (RecyclerView) findViewById(R.id.myRecyclerView);
        mFadeProgressBarRL = (RelativeLayout) findViewById(R.id.fadeProgressBarRL);
        mProgressBarFade = (ProgressBar) findViewById(R.id.progressBarFade);
        Utilities.setFadeProgressBarVisibility(false, mFadeProgressBarRL, mProgressBarFade);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(ProductActivity.this);
        mAdapter = new ProductAdapter(ProductActivity.this, mCursor, mFadeProgressBarRL, mProgressBarFade, TAG);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(mLayoutManager);

        mDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(false);

        mDrawerFragment.setSelectedPosition(1);

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

        ItemClickSupport.addTo(mRecyclerview).setOnItemClickListener(mItemClickListener);

        mEtSearch = (EditText) findViewById(R.id.editSearch);
        mEtSearch.setOnEditorActionListener(mEditSearchActionListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        mActionShop = (View) menu.findItem(R.id.action_shop).getActionView();
        Utilities.setAddCartOnClickListener(ProductActivity.this, mActionShop);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDelay(this);
        HttpHelper.getProductListing(ProductActivity.this, TAG);
        getSupportLoaderManager().initLoader(mLoaderId, null, this);
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
        getContentResolver().delete(ProductProvider.CONTENT_URI, null, null);
        Utilities.checkToCloseSideMenuForOnPause(this, mSideMenu, mRl, mDrawerFragment);
        LocalBroadcastManager.getInstance(getBaseContext()).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                if (intent.getBooleanExtra("success", false)) {
                    Utilities.refreshActionShop(ProductActivity.this, mActionShop);
                    Utilities.setFadeProgressBarVisibility(false, mFadeProgressBarRL, mProgressBarFade);
                } else if (intent.getBooleanExtra("failed", false)) {
                    Utilities.setFadeProgressBarVisibility(false, mFadeProgressBarRL, mProgressBarFade);
                }
            }
        }
    };

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (mLoaderId == id) {
            return new CursorLoader(ProductActivity.this, ProductProvider.CONTENT_URI, null, mSelection, mSelectionArgs, null);
        } else {
            return null;
        }
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (loader.getId() == mLoaderId) {
            if (data != null) {
                mCursor = data;
                mAdapter.swapCursor(data);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
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
    public void onBackPressed() {
        Utilities.checkToCloseSideMenu(this, mSideMenu, mRl, mDrawerFragment);
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
        }
    };


    private TextView.OnEditorActionListener mEditSearchActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (!mEtSearch.getText().toString().equals("")) {
                    mSelection = DBHelper.PRODUCT_COLUMN_TITLE + " LIKE ? ";
                    mSelectionArgs = new String[]{"%" + mEtSearch.getText().toString() + "%"};
                } else {
                    mSelection = null;
                    mSelectionArgs = null;
                }
                ProductActivity.this.getSupportLoaderManager().restartLoader(mLoaderId, null, ProductActivity.this);
                InputMethodManager imm = (InputMethodManager) ProductActivity.this.getSystemService(ProductActivity.this.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        }
    };
}
