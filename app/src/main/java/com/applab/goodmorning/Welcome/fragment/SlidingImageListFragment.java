package com.applab.goodmorning.Welcome.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.applab.goodmorning.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by user on 11-Mar-16.
 */
public class SlidingImageListFragment extends ListFragment {
    private ProgressBar mProgressBar;
    private ImageView mImgDisplay;
    private View mView;
    private String mUrl;
    private int mWidth;
    private int mHeight;
    private DisplayMetrics mDisplayMetrics;
    private String TAG = SlidingImageListFragment.class.getSimpleName();

    public static SlidingImageListFragment newInstance(String mUrl) {
        SlidingImageListFragment f = new SlidingImageListFragment();
        Bundle args = new Bundle();
        args.putString("mUrl", mUrl);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = getArguments() != null ? getArguments().getString("mUrl") : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.custom_image_sliding_pager, container, false);
        mProgressBar = (ProgressBar) mView.findViewById(R.id.progressBar);
        mImgDisplay = (ImageView) mView.findViewById(R.id.imgDisplay);
        mDisplayMetrics = getActivity().getResources().getDisplayMetrics();
        mHeight = mDisplayMetrics.heightPixels;
        mWidth = mDisplayMetrics.widthPixels;
        Glide.with(getActivity())
                .load(mUrl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>(mWidth, mHeight) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mImgDisplay.setImageBitmap(resource);
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
