package com.applab.goodmorning.News.fragment;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.os.Handler;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.News.activity.NewsDetailsActivity;
import com.applab.goodmorning.News.adapter.NewsAdapter;
import com.applab.goodmorning.News.dialog.DownloadDialogFragment;
import com.applab.goodmorning.News.model.News;
import com.applab.goodmorning.News.model.NewsItem;
import com.applab.goodmorning.News.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.ItemClickSupport;
import com.applab.goodmorning.Utilities.Utilities;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by user on 08-Mar-16.
 */
public class NewsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<News> mNews = new ArrayList<>();
    private NewsAdapter mAdapter;
    private String TAG;

    public static NewsFragment newInstance(String type) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TAG = getArguments().getString("type");
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.myRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new NewsAdapter(getActivity(), mNews);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(mItemClickListener);
        HttpHelper.getNews(getActivity(), TAG);
        return view;
    }

    private void setDelay(String tag) {
        if (tag.equals(getString(R.string.adv))) {
            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {
                    HttpHelper.getNews(getActivity(), TAG);
                }
            };
            handler.postDelayed(r, 100);
        } else if (tag.equals(getString(R.string.aa))) {
            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {
                    HttpHelper.getNews(getActivity(), TAG);
                }
            };
            handler.postDelayed(r, 200);
        } else if (tag.equals(getString(R.string.pr))) {
            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {
                    HttpHelper.getNews(getActivity(), TAG);
                }
            };
            handler.postDelayed(r, 300);
        } else if (tag.equals(getString(R.string.rad))) {
            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {
                    HttpHelper.getNews(getActivity(), TAG);
                }
            };
            handler.postDelayed(r, 400);
        } else if (tag.equals(getString(R.string.others))) {
            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {
                    HttpHelper.getNews(getActivity(), TAG);
                }
            };
            handler.postDelayed(r, 500);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setDelay(TAG);
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, iff);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                NewsItem newsItem = intent.getParcelableExtra("News");
                if (newsItem != null) {
                    if (newsItem.getNews() != null) {
                        mNews = new ArrayList<>(newsItem.getNews());
                        mAdapter.swapNews(mNews);
                    }
                }
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }

    private ItemClickSupport.OnItemClickListener mItemClickListener = new ItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
            if (mNews.get(position).getFile() != null) {
                String[] result = mNews.get(position).getFile().split("/");
                String fileName = getResources().getString(R.string.audio_file) + "/" + mNews.get(position).getId().toString() + "/" + result[result.length - 1];
                File direct = new File(Environment.getExternalStorageDirectory()
                        + "/" + getResources().getString(R.string.folder_name) + "/" + fileName);
                if (Utilities.localFileExists(direct.getPath())) {
                    Utilities.openDocument(direct.getPath(), getActivity());
                } else {
                    String folderName = getResources().getString(R.string.audio_file) + "/" + mNews.get(position).getId().toString();
                    DownloadDialogFragment downloadDialogFragment = DownloadDialogFragment.newInstance(getActivity(), mNews.get(position).getFile(), folderName);
                    downloadDialogFragment.show(getActivity().getSupportFragmentManager(), "");
                }
            } else {
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra("News", mNews.get(position));
                getActivity().startActivity(intent);
            }
        }
    };
}
