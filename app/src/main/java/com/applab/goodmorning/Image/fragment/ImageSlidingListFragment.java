package com.applab.goodmorning.Image.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.applab.goodmorning.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gesture_image_view.GestureImageView;

/**
 * Created by user on 24-Mar-16.
 */
public class ImageSlidingListFragment extends ListFragment {
    private ProgressBar mProgressBar;
    private GestureImageView mImgDisplay;
    private View mView;
    private String mUrl;
    private int mWidth;
    private int mHeight;
    private DisplayMetrics mDisplayMetrics;
    private String TAG = ImageSlidingListFragment.class.getSimpleName();

    public static ImageSlidingListFragment newInstance(String mUrl) {
        ImageSlidingListFragment f = new ImageSlidingListFragment();
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
        mView = inflater.inflate(R.layout.custom_image_zoom, container, false);
        mProgressBar = (ProgressBar) mView.findViewById(R.id.progressBar);
        mImgDisplay = (GestureImageView) mView.findViewById(R.id.imgDisplay);
        mDisplayMetrics = getActivity().getResources().getDisplayMetrics();
        mHeight = mDisplayMetrics.heightPixels;
        mWidth = mDisplayMetrics.widthPixels;
        String url = mUrl;
        if (mUrl.contains("/thumb")) {
            url = mUrl.replace("/thumb", "");
        }
        Glide.with(getActivity())
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>(mWidth, mHeight) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mImgDisplay.setImageBitmap(resource);
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
        Log.i(TAG, "Testing " + mUrl);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
