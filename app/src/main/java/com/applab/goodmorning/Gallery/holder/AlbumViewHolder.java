package com.applab.goodmorning.Gallery.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * Created by user on 30/3/2016.
 */
public class AlbumViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImgAlbum;
    private TextView mTxtName,mTxtNoPhoto;
    private ProgressBar mProgressBar;

    public AlbumViewHolder(View itemView) {
        super(itemView);
        mImgAlbum = (ImageView) itemView.findViewById(R.id.imgAlbum);
        mTxtName = (TextView) itemView.findViewById(R.id.txtName);
        mTxtNoPhoto = (TextView) itemView.findViewById(R.id.txtNoPhoto);
        mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
    }

    public ProgressBar getmProgressBar() {
        return mProgressBar;
    }

    public ImageView getmImgAlbum() {
        return mImgAlbum;
    }

    public TextView getmTxtName() {
        return mTxtName;
    }

    public TextView getmTxtNoPhoto() {
        return mTxtNoPhoto;
    }
}