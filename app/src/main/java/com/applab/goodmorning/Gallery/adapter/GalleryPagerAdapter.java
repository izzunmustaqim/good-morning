package com.applab.goodmorning.Gallery.adapter;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.applab.goodmorning.Gallery.fragment.PhotoFragment;
import com.applab.goodmorning.Gallery.fragment.VideoFragment;
import com.applab.goodmorning.R;

/**
 * Created by user on 08-Mar-16.
 */
public class GalleryPagerAdapter extends FragmentPagerAdapter {
    String[] mTabs;
    Context mContext;

    public GalleryPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mTabs = context.getResources().getStringArray(R.array.gallery_tabs);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment item = null;
        VideoFragment videoFragment = new VideoFragment();
        PhotoFragment photoFragment = new PhotoFragment();
        if (position == 0) {
            item = photoFragment;
        } else {
            item = videoFragment;
        }
        return item;
    }

    @Override
    public int getCount() {
        return mContext.getResources().getStringArray(R.array.gallery_tabs).length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }
}
