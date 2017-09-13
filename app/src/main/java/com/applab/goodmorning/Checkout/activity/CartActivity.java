package com.applab.goodmorning.Checkout.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.Checkout.adapter.CartAdapter;
import com.applab.goodmorning.Checkout.adapter.CheckOutPagerAdapter;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.CustomViewPager;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Welcome.model.Products;

import java.util.ArrayList;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 9/3/2016
 * -- Description: CartActivity .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class CartActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private CustomViewPager mPager;
    private CheckOutPagerAdapter mAdapter;
    private View mCustomCartProcess;
    private int mPosition = 0;
    private ImageView mImg1;
    private ImageView mImg2;
    private ImageView mImg3;
    private ImageView mImg4;
    private TextView mTxtCart;
    private TextView mTxtDelivery;
    private TextView mTxtPaymentMethod;
    private TextView mTxtConfirmation;
    private int mTotalFragment = 4;
    private RelativeLayout mRlFadeForProgressBar;
    private ProgressBar mProgressBar;
    private String TAG = CartActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_check_out));
        mToolbar.setFitsSystemWindows(false);
        mToolbar.setPadding(0, 0, 0, 0);
        ImageView mImg = (ImageButton) mToolbar.findViewById(R.id.img);
        mImg.setVisibility(View.GONE);
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);
        mCustomCartProcess = findViewById(R.id.custom_cart_process);
        mImg1 = (ImageView) mCustomCartProcess.findViewById(R.id.img1);
        mImg2 = (ImageView) mCustomCartProcess.findViewById(R.id.img2);
        mImg3 = (ImageView) mCustomCartProcess.findViewById(R.id.img3);
        mImg4 = (ImageView) mCustomCartProcess.findViewById(R.id.img4);
        mTxtCart = (TextView) mCustomCartProcess.findViewById(R.id.txtCart);
        mTxtDelivery = (TextView) mCustomCartProcess.findViewById(R.id.txtDelivery);
        mTxtPaymentMethod = (TextView) mCustomCartProcess.findViewById(R.id.txtPaymentMethod);
        mTxtConfirmation = (TextView) mCustomCartProcess.findViewById(R.id.txtConfirmation);

        mImg1.setImageResource(R.mipmap.number1_brown);
        mImg2.setImageResource(R.mipmap.number2_brown);
        mImg3.setImageResource(R.mipmap.number3_brown);
        mImg4.setImageResource(R.mipmap.number4_brown);
        mTxtCart.setVisibility(View.VISIBLE);
        mTxtDelivery.setVisibility(View.GONE);
        mTxtPaymentMethod.setVisibility(View.GONE);
        mTxtConfirmation.setVisibility(View.GONE);

        mAdapter = new CheckOutPagerAdapter(getSupportFragmentManager(), this, mTotalFragment);
        mPager = (CustomViewPager) findViewById(R.id.pager);
        mPager.setPagingEnabled(false);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(mPosition);
        mPager.addOnPageChangeListener(mPagerOnPageChangeListener);

        mRlFadeForProgressBar = (RelativeLayout) findViewById(R.id.fadeRLForProgressBar);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        Utilities.setFadeProgressBarVisibility(false, mRlFadeForProgressBar, mProgressBar);
    }

    private ViewPager.OnPageChangeListener mPagerOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i2) {
        }

        @Override
        public void onPageSelected(int i) {
            mPosition = i;
            mImg1.setImageResource(R.mipmap.number1_brown);
            mImg2.setImageResource(R.mipmap.number2_brown);
            mImg3.setImageResource(R.mipmap.number3_brown);
            mImg4.setImageResource(R.mipmap.number4_brown);
            mTxtCart.setVisibility(View.VISIBLE);
            mTxtDelivery.setVisibility(View.GONE);
            mTxtPaymentMethod.setVisibility(View.GONE);
            mTxtConfirmation.setVisibility(View.GONE);
            if (mPosition == 1) {
                mImg1.setImageResource(R.mipmap.checkout_brown_transparent);
                mTxtCart.setVisibility(View.GONE);
                mTxtDelivery.setVisibility(View.VISIBLE);
            } else if (mPosition == 2) {
                mImg1.setImageResource(R.mipmap.checkout_brown_transparent);
                mImg2.setImageResource(R.mipmap.checkout_brown_transparent);
                mTxtCart.setVisibility(View.GONE);
                mTxtPaymentMethod.setVisibility(View.VISIBLE);
            } else if (mPosition == 3) {
                mImg1.setImageResource(R.mipmap.checkout_brown_transparent);
                mImg2.setImageResource(R.mipmap.checkout_brown_transparent);
                mImg3.setImageResource(R.mipmap.checkout_brown_transparent);
                mTxtCart.setVisibility(View.GONE);
                mTxtConfirmation.setVisibility(View.VISIBLE);
            } else {
                mImg1.setImageResource(R.mipmap.number1_brown);
                mImg2.setImageResource(R.mipmap.number2_brown);
                mImg3.setImageResource(R.mipmap.number3_brown);
                mImg4.setImageResource(R.mipmap.number4_brown);
                mTxtCart.setVisibility(View.VISIBLE);
                mTxtDelivery.setVisibility(View.GONE);
                mTxtPaymentMethod.setVisibility(View.GONE);
                mTxtConfirmation.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mPosition != 0) {
                mPosition -= 1;
                mPager.setCurrentItem(mPosition);
            } else {
                finish();
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, iff);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                if (intent.getBooleanExtra("isLock", false)) {
                    Utilities.setFadeProgressBarVisibility(true, mRlFadeForProgressBar, mProgressBar);
                } else if (intent.getBooleanExtra("isSlide", false)) {
                    mPager.setCurrentItem(intent.getIntExtra("position", 0));
                } else {
                    Utilities.setFadeProgressBarVisibility(false, mRlFadeForProgressBar, mProgressBar);
                }
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onBackPressed() {
        if (mPosition != 0) {
            mPosition -= 1;
            mPager.setCurrentItem(mPosition);
        } else {
            finish();
        }
    }
}