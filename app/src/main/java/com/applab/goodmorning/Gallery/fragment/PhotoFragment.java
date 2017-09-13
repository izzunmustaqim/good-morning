package com.applab.goodmorning.Gallery.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.Gallery.activity.GalleryActivity;
import com.applab.goodmorning.Gallery.adapter.AlbumAdapter;
import com.applab.goodmorning.Gallery.adapter.ImageAdapter;
import com.applab.goodmorning.Gallery.model.Album;
import com.applab.goodmorning.Gallery.model.AlbumItems;
import com.applab.goodmorning.Gallery.model.Gallery;
import com.applab.goodmorning.Gallery.model.Photo;
import com.applab.goodmorning.Gallery.model.PhotoItems;
import com.applab.goodmorning.Gallery.webapi.HttpHelper;
import com.applab.goodmorning.Image.activity.ImageSlidingActivty;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.ItemClickSupport;
import com.applab.goodmorning.Utilities.Utilities;

import java.util.ArrayList;

/**
 * Created by user on 08-Mar-16.
 */
public class PhotoFragment extends Fragment {
    private RecyclerView mAlbumRecyclerview;
    private RecyclerView mPhotoRecyclerview;
    private ImageAdapter mPhotoAdapter;
    private AlbumAdapter mAlbumAdapter;
    private AlbumItems mAlbumItems;
    private PhotoItems mPhotoItems;
    private String TAG = GalleryActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        mAlbumRecyclerview = (RecyclerView) view.findViewById(R.id.albumRecyclerView);
        mPhotoRecyclerview = (RecyclerView) view.findViewById(R.id.photoRecyclerView);
        mAlbumRecyclerview.setHasFixedSize(true);

        mAlbumRecyclerview.setVisibility(View.VISIBLE);
        mPhotoRecyclerview.setVisibility(View.GONE);

        mPhotoAdapter = new ImageAdapter(getActivity(), mPhotoItems);
        mAlbumAdapter = new AlbumAdapter(getActivity(), mAlbumItems);

        mAlbumRecyclerview.setAdapter(mAlbumAdapter);
        mAlbumRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ItemClickSupport.addTo(mAlbumRecyclerview).setOnItemClickListener(mItemClickAlbumListener);

        mPhotoRecyclerview.setAdapter(mPhotoAdapter);
        mPhotoRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        ItemClickSupport.addTo(mPhotoRecyclerview).setOnItemClickListener(mItemClickPhotoListener);
        HttpHelper.getAlbum(getActivity(), TAG);
        return view;
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(GalleryActivity.class.getSimpleName())) {
                if (intent.getBooleanExtra("isBack", false)) {
                    if (mPhotoRecyclerview.getVisibility() == View.VISIBLE) {
                        mPhotoRecyclerview.setVisibility(View.GONE);
                        mAlbumRecyclerview.setVisibility(View.VISIBLE);
                    }
                } else if (intent.getBooleanExtra("isAlbum", false)) {
                    mAlbumItems = intent.getParcelableExtra("Albums");
                    mAlbumAdapter.swapAlbum(mAlbumItems);
                } else if (intent.getBooleanExtra("isPhoto", false)) {
                    mPhotoItems = intent.getParcelableExtra("Photos");
                    mPhotoAdapter.swapPhoto(mPhotoItems);
                }
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, iff);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }

    private ItemClickSupport.OnItemClickListener mItemClickAlbumListener = new ItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
            TextView txtName = (TextView) v.findViewById(R.id.txtName);
            Album album = (Album) txtName.getTag();
            mAlbumRecyclerview.setVisibility(View.GONE);
            mPhotoRecyclerview.setVisibility(View.VISIBLE);
            Intent intent = new Intent(GalleryActivity.class.getSimpleName());
            intent.putExtra("isPhoto", true);
            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
            HttpHelper.getPhoto(getActivity(), TAG, album);
        }
    };

    private ItemClickSupport.OnItemClickListener mItemClickPhotoListener = new ItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
            ImageView imgPhoto = (ImageView) v.findViewById(R.id.imgPlay);
            RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.rl);
            PhotoItems photoItems = (PhotoItems) imgPhoto.getTag();
            Intent intent = new Intent(getActivity(), ImageSlidingActivty.class);
            ArrayList<String> arr = new ArrayList<>();
            for (int i = 0; i < photoItems.getPhotos().size(); i++) {
                arr.add(photoItems.getPhotos().get(i).getPhoto());
            }
            intent.putExtra("url", arr);
            intent.putExtra("position", (Integer) rl.getTag());
            startActivity(intent);
        }
    };
}
