package com.applab.goodmorning.Promotions.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
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
import android.widget.Toast;

import com.applab.goodmorning.Image.activity.ImageSlidingActivty;
import com.applab.goodmorning.Login.model.Token;
import com.applab.goodmorning.Menu.fragment.NavigationDrawerFragment;
import com.applab.goodmorning.News.adapter.NewsAdapter;
import com.applab.goodmorning.News.model.News;
import com.applab.goodmorning.Promotions.adapter.PromotionAdapter;
import com.applab.goodmorning.Promotions.dialog.PromotionDialog;
import com.applab.goodmorning.Promotions.model.Promotion;
import com.applab.goodmorning.Promotions.model.PromotionItem;
import com.applab.goodmorning.Promotions.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.ItemClickSupport;
import com.applab.goodmorning.Utilities.Utilities;

import java.net.URI;
import java.util.ArrayList;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 11/4/2016
 * -- Description: CartActivity .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class PromotionsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<News> mNews = new ArrayList<>();
    private PromotionAdapter mAdapter;
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
    private ArrayList<Promotion> mPromotion = new ArrayList<>();

    private Token mToken;
    private String TAG = PromotionsActivity.class.getSimpleName();
    private int mLoaderId = 2323;
    private View mActionShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotions);

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_promotions));

        mDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(false);

        mDrawerFragment.setSelectedPosition(6);

        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PromotionAdapter(this, mPromotion);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(mItemClickListener);

        mDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerFragment.setSelectedPosition(5);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_menu) {
            Utilities.setSideMenuVisible(true, mSideMenu, mRl);
            return true;
        } else {
            return mDrawerFragment.mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
        }
    }

    private ItemClickSupport.OnItemClickListener mItemClickListener = new ItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
            if (mPromotion.get(position).getFile() != null || mPromotion.get(position).getImage() != null) {
                String uri = mPromotion.get(position).getFile();
                String imageUri = mPromotion.get(position).getImage();
                String extension = uri.substring(uri.lastIndexOf(".") + 1);
                String imageExtension = imageUri.substring(imageUri.lastIndexOf(".") + 1);

                if (!extension.equals(imageExtension)) {
                    PromotionDialog promotionDialog = PromotionDialog.newInstance(PromotionsActivity.this, mPromotion.get(position));
                    promotionDialog.show(getSupportFragmentManager(), "");
                } else {
                    setClicklable(position);
                }
            }
        }
    };

    private void setClicklable(int position) {
        if (mPromotion.get(position).getFile() != null) {
            String uri = mPromotion.get(position).getFile();
            String extension = uri.substring(uri.lastIndexOf(".") + 1);
            if (extension.equals("jpg") || extension.equals("png")) {
                Intent intent = new Intent(PromotionsActivity.this, ImageSlidingActivty.class);
                ArrayList<String> arr = new ArrayList<>();
                arr.add(mPromotion.get(position).getFile());
                intent.putExtra("url", arr);
                intent.putExtra("position", 0);
                startActivity(intent);
            } else if (extension.equals("pdf")) {
                Utilities.openWeb(PromotionsActivity.this, mPromotion.get(position).getFile());
            }
        } else {
            Utilities.showError(PromotionsActivity.this, "", getString(R.string.no_file));
        }
    }

    private void setImageZoom(String image) {
        Intent intent = new Intent(PromotionsActivity.this, ImageSlidingActivty.class);
        ArrayList<String> arr = new ArrayList<>();
        arr.add(image);
        intent.putExtra("url", arr);
        intent.putExtra("position", 0);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        mActionShop = (View) menu.findItem(R.id.action_shop).getActionView();
        Utilities.setAddCartOnClickListener(PromotionsActivity.this, mActionShop);
        return true;
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mDrawerFragment.setOpenCloseDrawer();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utilities.checkSideMenu(this, mSideMenu, mRl);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Utilities.checkToCloseSideMenuForOnPause(this, mSideMenu, mRl, mDrawerFragment);
        LocalBroadcastManager.getInstance(PromotionsActivity.this).unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDelay(this);
        HttpHelper.getPromotion(PromotionsActivity.this, TAG);
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(PromotionsActivity.this).registerReceiver(broadcastReceiver, iff);
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
                if (intent.getBooleanExtra("isPDF", false)) {
                    Promotion promotion = intent.getParcelableExtra("Promotion");
                    Utilities.openWeb(PromotionsActivity.this, promotion.getFile());
                } else if (intent.getBooleanExtra("isPic", false)) {
                    Promotion promotion = intent.getParcelableExtra("Promotion");
                    setImageZoom(promotion.getImage());
                } else {
                    PromotionItem promotionItem;
                    promotionItem = intent.getParcelableExtra("Promotion");
                    if (promotionItem != null) {
                        if (promotionItem.getItems() != null) {
                            mPromotion = new ArrayList<>(promotionItem.getItems());
                            mAdapter.swapPromotion(mPromotion);
                        }
                    }
                }
            }
        }
    };
}
