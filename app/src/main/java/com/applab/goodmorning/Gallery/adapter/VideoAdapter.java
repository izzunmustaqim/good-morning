package com.applab.goodmorning.Gallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.Gallery.holder.ImageViewHolder;
import com.applab.goodmorning.Gallery.model.Gallery;
import com.applab.goodmorning.Gallery.model.Video;
import com.applab.goodmorning.Gallery.model.VideoItems;
import com.applab.goodmorning.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by user on 10-Mar-16.
 */
public class VideoAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private VideoItems mVideoItems;

    public VideoAdapter(final Context context, final VideoItems videoItems) {
        mContext = context;
        mVideoItems = videoItems;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_photo_gallery, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        Video video = mVideoItems.getVideos().get(position);
        holder.getImgPlay().setTag(video);
        Glide.with(mContext)
                .load(mContext.getString(R.string.youtube_base_url)
                        + video.getYoutubeId()
                        + mContext.getString(R.string.youtube_thumbnail))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.video)
                .into(holder.getImgPhotos());
    }

    @Override
    public int getItemCount() {
        return mVideoItems == null ? 0 : mVideoItems.getVideos() == null ? 0 : mVideoItems.getVideos().size();
    }

    public void swapVideo(VideoItems videoItems) {
        mVideoItems = videoItems;
        this.notifyDataSetChanged();
    }
}
