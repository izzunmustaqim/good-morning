package com.applab.goodmorning.News.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.applab.goodmorning.News.fragment.NewsFragment;
import com.applab.goodmorning.R;

/**
 * Created by user on 08-Mar-16.
 */
public class NewsManagerAdapter extends FragmentPagerAdapter {
    String[] mTabs;
    Context mContext;

    public NewsManagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mTabs = context.getResources().getStringArray(R.array.news_tabs);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return NewsFragment.newInstance(mContext.getString(R.string.adv));
        } else if (position == 1) {
            return NewsFragment.newInstance(mContext.getString(R.string.aa));
        } else if (position == 2) {
            return NewsFragment.newInstance(mContext.getString(R.string.pr));
        } else if (position == 3) {
            return NewsFragment.newInstance(mContext.getString(R.string.rad));
        } else if (position == 4) {
            return NewsFragment.newInstance(mContext.getString(R.string.others));
        }
        return null;
    }

    @Override
    public int getCount() {
        return mContext.getResources().getStringArray(R.array.news_tabs).length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }
}
