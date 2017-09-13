package com.applab.goodmorning.Welcome.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.applab.goodmorning.R;
import com.applab.goodmorning.Welcome.fragment.ProductsFragment;
import com.applab.goodmorning.Welcome.fragment.PromotionFragment;

/**
 * Created by user on 04-Mar-16.
 */
public class WelcomePagerAdapter extends FragmentPagerAdapter {
    String[] mTabs;
    Context mContext;

    public WelcomePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mTabs = context.getResources().getStringArray(R.array.welcome_tabs);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment item = null;
        ProductsFragment productsFragment = new ProductsFragment();
        PromotionFragment promotionFragment = new PromotionFragment();
        if (position == 0) {
            item = productsFragment;
        } else {
            item = promotionFragment;
        }

        return item;
    }

    @Override
    public int getCount() {
        return mContext.getResources().getStringArray(R.array.welcome_tabs).length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }
}
