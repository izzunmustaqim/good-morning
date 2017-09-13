package com.applab.goodmorning.Image.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applab.goodmorning.Image.adapter.ImageSlidingAdapter;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

public class ImageSlidingActivty extends AppCompatActivity {

    private ImageSlidingAdapter adapter;
    private ViewPager mViewPager;
    private int mPosition;
    private TextView mTxtTitle;
    private LinearLayout mBtnSave;
    private int mSelectedPage;
    private ArrayList<String> mArrUrl = new ArrayList<>();
    private boolean mIsDownload = false;
    private String mStoreFolderLocation;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_sliding_activty);
        mTxtTitle = (TextView) findViewById(R.id.txtTitle);
        mBtnSave = (LinearLayout) findViewById(R.id.btnSave);
        mBtnSave.setVisibility(View.GONE);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPosition = getIntent().getIntExtra("position", 0);
        mArrUrl = getIntent().getStringArrayListExtra("url");
        mStoreFolderLocation = getIntent().getStringExtra("storeFolderLocation");
        mIsDownload = getIntent().getBooleanExtra("isDownload", false);
        adapter = new ImageSlidingAdapter(getSupportFragmentManager(), ImageSlidingActivty.this, mArrUrl);
        mViewPager.setAdapter(adapter);

        String[] result = mArrUrl.get(mPosition).split("/");
        mTxtTitle.setText(result[result.length - 1]);
        mBtnSave.setTag(mArrUrl.get(mPosition));

        mViewPager.setCurrentItem(mPosition);
        mSelectedPage = mPosition;
        mViewPager.addOnPageChangeListener(mViewPagerOnPageChangeListener);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(Color.BLACK);
    }

    private View.OnClickListener mBtnSaveOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Utilities.isConnectingToInternet(getBaseContext())) {
                Utilities.downloadFile(mBtnSave.getTag().toString(), ImageSlidingActivty.this, mStoreFolderLocation);
            } else {
                Utilities.showError(ImageSlidingActivty.this, Utilities.CODE_CONNECTION, Utilities.CONNECTION);
            }
        }
    };

    private ViewPager.OnPageChangeListener mViewPagerOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i2) {
        }

        @Override
        public void onPageSelected(int i) {
            String[] result = mArrUrl.get(i).split("/");
            mTitle = result[result.length - 1];
            mTxtTitle.setText(result[result.length - 1]);
            mBtnSave.setTag(mArrUrl.get(i));
            mSelectedPage = i;
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public void handleAction(View view) {
        if (mTxtTitle.getVisibility() == View.GONE) {
            mTxtTitle.setVisibility(View.VISIBLE);
            mTxtTitle.setText(mTitle);
        } else {
            mTxtTitle.setVisibility(View.GONE);
        }
        if (mIsDownload) {
            if (mBtnSave.getVisibility() == View.GONE) {
                if (mBtnSave.getVisibility() == View.GONE) {
                    Utilities.expand(mBtnSave);
                } else {
                    Utilities.collapse(mBtnSave);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
