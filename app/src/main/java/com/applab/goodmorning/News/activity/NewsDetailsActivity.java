package com.applab.goodmorning.News.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.applab.goodmorning.Image.activity.ImageSlidingActivty;
import com.applab.goodmorning.News.adapter.NewsDetailsAdapter;
import com.applab.goodmorning.News.binder.NewsDetailsTextBinder;
import com.applab.goodmorning.News.model.News;
import com.applab.goodmorning.News.model.NewsItem;
import com.applab.goodmorning.News.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.ItemClickSupport;
import com.applab.goodmorning.Welcome.model.Products;

import java.util.ArrayList;

public class NewsDetailsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private ImageButton mImg;
    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManger;
    private NewsDetailsAdapter mAdapter;

    private TextView mTxtDate;
    private TextView mTxtTitle;
    private TextView mTxtDesc;
    private TextView mTxtBottomDate;

    private News mNews = new News();
    private String TAG = NewsDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        mNews = getIntent().getParcelableExtra("News");

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_news));

        mImg = (ImageButton) mToolbar.findViewById(R.id.img);
        mImg.setVisibility(View.GONE);

        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManger = new LinearLayoutManager(this);
        mAdapter = new NewsDetailsAdapter(NewsDetailsActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManger);

        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(NewsDetailsActivity.this).registerReceiver(broadcastReceiver, iff);
        HttpHelper.getNewsDetails(NewsDetailsActivity.this, TAG, mNews.getId());
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(NewsDetailsActivity.this).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                NewsItem newsItem = intent.getParcelableExtra("NewsDetailsBroadcast");
                if (newsItem != null) {
                    if (newsItem.getNews() != null) {
                        mAdapter.setNewsData(newsItem.getNews().get(0));
                    }
                }
            }
        }
    };
}
