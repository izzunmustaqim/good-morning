package com.applab.goodmorning.Enquiry.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.applab.goodmorning.ContactUs.fragment.ContactUsFragment;
import com.applab.goodmorning.Enquiry.fragment.GeneralFragment;
import com.applab.goodmorning.Enquiry.fragment.OEMFragment;
import com.applab.goodmorning.Enquiry.fragment.RetailDistributorFragment;
import com.applab.goodmorning.R;

/**
 * Created by user on 12/4/2016.
 */
public class EnquiryPagerAdapter extends FragmentPagerAdapter {
    String[] mTabs;
    Context mContext;

    public EnquiryPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mTabs = context.getResources().getStringArray(R.array.enquiry_tabs);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new GeneralFragment();
        } else if (position == 1) {
            return new RetailDistributorFragment();
        } else {
            return new OEMFragment();
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