package com.applab.goodmorning.Image.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.applab.goodmorning.Image.fragment.ImageSlidingListFragment;

import java.util.ArrayList;

/**
 * Created by user on 24-Mar-16.
 */
public class ImageSlidingAdapter extends FragmentStatePagerAdapter {
    private Activity mActivity;
    private ArrayList<String> mImagePaths = new ArrayList<>();

    public ImageSlidingAdapter(FragmentManager fm, Activity activity, ArrayList<String> imagePaths) {
        super(fm);
        mActivity = activity;
        mImagePaths = imagePaths;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageSlidingListFragment.newInstance(mImagePaths.get(position));
    }

    @Override
    public int getCount() {
        return this.mImagePaths.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
