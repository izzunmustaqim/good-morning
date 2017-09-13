package com.applab.goodmorning.Gallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.Gallery.holder.ImageViewHolder;
import com.applab.goodmorning.Gallery.model.Gallery;
import com.applab.goodmorning.Gallery.model.Photo;
import com.applab.goodmorning.Gallery.model.PhotoItems;
import com.applab.goodmorning.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by user on 10-Mar-16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private PhotoItems mPhotoItems;

    public ImageAdapter(final Context context, final PhotoItems photoItems) {
        mContext = context;
        mPhotoItems = photoItems;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_photo_gallery, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Photo photo = mPhotoItems.getPhotos().get(position);
        Glide.with(mContext)
                .load(photo.getPhoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.photo)
                .into(holder.getImgPhotos());
        holder.getImgPlay().setTag(mPhotoItems);
        holder.getmRl().setTag(position);
    }

    @Override
    public int getItemCount() {
        return mPhotoItems == null ? 0 : mPhotoItems.getPhotos().size();
    }

    public void swapPhoto(PhotoItems photoItems) {
        mPhotoItems = photoItems;
        this.notifyDataSetChanged();
    }
}
