package com.applab.goodmorning.Event.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.applab.goodmorning.Event.fragment.MonthFragment;
import com.applab.goodmorning.Event.fragment.ScheduleFragment;
import com.applab.goodmorning.R;

/**
 * Created by user on 4/3/2016.
 */
public class EventPagerAdapter extends FragmentPagerAdapter {
    String[] mTabs;
    Context mContext;

    public EventPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mTabs = new String[]{String.valueOf(R.mipmap.list_brown_transparent) + "," +
                String.valueOf(R.mipmap.list_brown),
                String.valueOf(R.mipmap.calendar_brown_transparent) + "," +
                        String.valueOf(R.mipmap.calendar_brown)};
    }

    @Override
    public Fragment getItem(int position) {
        Fragment item = null;
        ScheduleFragment scheduleFragment = new ScheduleFragment();
        MonthFragment monthFragment = new MonthFragment();
        if (position == 0) {
            item = scheduleFragment;
        } else if (position == 1) {
            item = monthFragment;
        }
        return item;
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }
}