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

import com.applab.goodmorning.Gallery.adapter.VideoAdapter;
import com.applab.goodmorning.Gallery.model.Video;
import com.applab.goodmorning.Gallery.model.VideoItems;
import com.applab.goodmorning.Gallery.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.ItemClickSupport;
import com.applab.goodmorning.Utilities.Utilities;

/**
 * Created by user on 08-Mar-16.
 */
public class VideoFragment extends Fragment {
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerview;
    private VideoAdapter mAdapter;
    private VideoItems mVideoItems;
    private String TAG = VideoFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.myRecyclerView);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mAdapter = new VideoAdapter(getActivity(), mVideoItems);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(mLayoutManager);
        ItemClickSupport.addTo(mRecyclerview).setOnItemClickListener(mItemClickListener);
        return view;
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                mVideoItems = intent.getParcelableExtra("Videos");
                mAdapter.swapVideo(mVideoItems);
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        HttpHelper.getVideo(getActivity(), TAG);
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, iff);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }

    private ItemClickSupport.OnItemClickListener mItemClickListener = new ItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
            ImageView imgPhotos = (ImageView) v.findViewById(R.id.imgPlay);
            Video video = (Video) imgPhotos.getTag();
            Utilities.watchYoutubeVideo(getActivity(), video.getYoutubeUrl(), video.getYoutubeId());
        }
    };
}
