package com.applab.goodmorning.Gallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.Gallery.holder.AlbumViewHolder;
import com.applab.goodmorning.Gallery.model.Album;
import com.applab.goodmorning.Gallery.model.AlbumItems;
import com.applab.goodmorning.Gallery.model.Gallery;
import com.applab.goodmorning.Gallery.model.PhotoItems;
import com.applab.goodmorning.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;

/**
 * Created by user on 30/3/2016.
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private AlbumItems mAlbumItems;

    public AlbumAdapter(final Context context, final AlbumItems albumItems) {
        mContext = context;
        mAlbumItems = albumItems;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_album, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AlbumViewHolder holder, int position) {
        Album album = mAlbumItems.getAlbums().get(position);
        holder.getmTxtName().setText(album.getAlbumTitle());
        holder.getmTxtName().setTag(album);
        holder.getmTxtNoPhoto().setText(String.valueOf(album.getPhotoCount()));
        Glide.with(mContext)
                .load(album.getCoverPhoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new GlideDrawableImageViewTarget(holder.getmImgAlbum()) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        holder.getmProgressBar().setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mAlbumItems == null ? 0 : mAlbumItems.getAlbums().size();
    }

    public void swapAlbum(AlbumItems albumItems) {
        mAlbumItems = albumItems;
        this.notifyDataSetChanged();
    }
}
