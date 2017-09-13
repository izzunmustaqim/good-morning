package com.applab.goodmorning.ContactUs.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.applab.goodmorning.ContactUs.fragment.ContactUsFragment;
import com.applab.goodmorning.R;

/**
 * Created by user on 7/4/2016.
 */
public class ContactUsPagerAdapter extends FragmentPagerAdapter {
    String[] mTabs;
    Context mContext;

    public ContactUsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mTabs = context.getResources().getStringArray(R.array.contact_us_tabs);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return ContactUsFragment.newInstance(position);
        } else {
            return ContactUsFragment.newInstance(position);
        }
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