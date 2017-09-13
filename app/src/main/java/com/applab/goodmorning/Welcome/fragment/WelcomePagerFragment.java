package com.applab.goodmorning.Welcome.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.R;
import com.applab.goodmorning.SlidingTabs.SlidingTabLayout;
import com.applab.goodmorning.Welcome.activity.WelcomeActivity;
import com.applab.goodmorning.Welcome.adapter.WelcomePagerAdapter;

public class WelcomePagerFragment extends Fragment {
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private int mPage = 0;
    private String TAG = WelcomeActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome_pager, container, false);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(2);
        mPager.setAdapter(new WelcomePagerAdapter(getChildFragmentManager(), getActivity()));
        mPager.addOnPageChangeListener(mPagerOnPageChangeListener);
        mTabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText, R.id.tabNumber, R.id.tabIcon);
        mTabs.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.color_white));
        mTabs.setSelectedIndicatorColors(ContextCompat.getColor(getActivity(), R.color.color_primary));
        mTabs.setViewPager(mPager);
        mPager.setCurrentItem(mPage);
        return view;
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
