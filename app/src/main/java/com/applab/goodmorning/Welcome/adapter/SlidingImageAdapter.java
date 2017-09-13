package com.applab.goodmorning.Welcome.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.applab.goodmorning.Welcome.fragment.SlidingImageListFragment;
import com.applab.goodmorning.Welcome.model.Banner;

import java.util.ArrayList;

/**
 * Created by user on 14-Mar-16.
 */
public class SlidingImageAdapter extends FragmentStatePagerAdapter {
    private Activity mActivity;
    private ArrayList<Banner> mImagePaths = new ArrayList<>();

    public SlidingImageAdapter(FragmentManager fm, Activity activity, ArrayList<Banner> imagePaths) {
        super(fm);
        mActivity = activity;
        mImagePaths = imagePaths;
    }

    @Override
    public Fragment getItem(int position) {
        return SlidingImageListFragment.newInstance(mImagePaths.get(position).getMobileImageUrl());
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return this.mImagePaths == null ? 0 : mImagePaths.size() > 0 ? mImagePaths.size() : 0;
    }

    public void swapImagePaths(ArrayList<Banner> imgPaths) {
        this.mImagePaths = imgPaths;
        this.notifyDataSetChanged();
    }
}
