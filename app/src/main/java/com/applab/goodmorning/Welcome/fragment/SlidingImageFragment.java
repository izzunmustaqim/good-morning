package com.applab.goodmorning.Welcome.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.applab.goodmorning.R;
import com.applab.goodmorning.Welcome.adapter.SlidingImageAdapter;
import com.applab.goodmorning.Welcome.adapter.SlidingImageDotsAdapter;
import com.applab.goodmorning.Welcome.model.BannerItems;
import com.applab.goodmorning.Welcome.webapi.HttpHelper;

import java.util.ArrayList;

/**
 * Created by user on 11-Mar-16.
 */
public class SlidingImageFragment extends Fragment {
    public static final String TAG = SlidingImageFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RelativeLayout mBtnLeft, mBtnRight;
    private ViewPager mPager;
    private SlidingImageAdapter mAdapter;
    private SlidingImageDotsAdapter mAdapterDots;
    private int mPosition = 0;
    private BannerItems mBannerItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sliding_image, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mAdapterDots = new SlidingImageDotsAdapter(getActivity(), new BannerItems().getBanners());
        mRecyclerView.setAdapter(mAdapterDots);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mBtnLeft = (RelativeLayout) view.findViewById(R.id.btnLeft);
        mBtnRight = (RelativeLayout) view.findViewById(R.id.btnRight);

        mAdapter = new SlidingImageAdapter(getActivity().getSupportFragmentManager(), getActivity(), new BannerItems().getBanners());
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(mPosition);
        mPager.addOnPageChangeListener(mPagerOnPageChangeListener);

        mBtnLeft.setOnClickListener(mBtnLeftOnClickListener);
        mBtnRight.setOnClickListener(mBtnRightOnClickListener);
        return view;
    }

    private ViewPager.OnPageChangeListener mPagerOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mPosition = position;
            mAdapterDots.swapDotsPosition(position);
            mRecyclerView.scrollToPosition(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private View.OnClickListener mBtnLeftOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mPosition != 0) {
                mPosition -= 1;
                mPager.setCurrentItem(mPosition);
            }
        }
    };

    private View.OnClickListener mBtnRightOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mPosition != mBannerItems.getBanners().size() - 1) {
                mPosition += 1;
                mPager.setCurrentItem(mPosition);
            }
        }
    };

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                mBannerItems = intent.getParcelableExtra("Banners");
                mAdapter.swapImagePaths(mBannerItems.getBanners());
                mAdapterDots.swapImagePaths(mBannerItems.getBanners());
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        //HttpHelper.getBanner(getActivity(), TAG);
        setDelay();
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, iff);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }

    public void setDelay() {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                HttpHelper.getBanner(getActivity(), TAG);
            }
        };
        handler.postDelayed(r, 100);
    }
}
