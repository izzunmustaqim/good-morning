package com.applab.goodmorning.Welcome.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.R;
import com.applab.goodmorning.Welcome.holder.SlidingImageDotsHolder;
import com.applab.goodmorning.Welcome.model.Banner;

import java.util.ArrayList;

/**
 * Created by user on 14-Mar-16.
 */
public class SlidingImageDotsAdapter extends RecyclerView.Adapter<SlidingImageDotsHolder> {
    private LayoutInflater mInflater;
    private ArrayList<Banner> mArrSelected;
    private Context mContext;
    private int mPosition = 0;

    public SlidingImageDotsAdapter(Context context, ArrayList<Banner> arrSelected) {
        this.mInflater = LayoutInflater.from(context);
        this.mArrSelected = arrSelected;
        this.mContext = context;
    }

    @Override
    public SlidingImageDotsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_image_dot, parent, false);
        return new SlidingImageDotsHolder(view);
    }

    @Override
    public void onBindViewHolder(SlidingImageDotsHolder holder, int position) {
        if (position == mPosition) {
            holder.getmImgDot().setBackground(ContextCompat.getDrawable(mContext, R.drawable.selected_dot));
        } else {
            holder.getmImgDot().setBackground(ContextCompat.getDrawable(mContext, R.drawable.unselected_dots));
        }
    }

    @Override
    public int getItemCount() {
        return mArrSelected == null ? 0 : mArrSelected.size();
    }

    public void swapDotsPosition(int position) {
        mPosition = position;
        this.notifyDataSetChanged();
    }

    public void swapImagePaths(ArrayList<Banner> imgPaths) {
        mPosition = 0;
        this.mArrSelected = imgPaths;
        this.notifyDataSetChanged();
    }
}
