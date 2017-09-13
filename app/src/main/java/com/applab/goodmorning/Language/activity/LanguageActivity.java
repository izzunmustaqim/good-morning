package com.applab.goodmorning.Language.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.applab.goodmorning.Language.adapter.LanguageAdapter;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Settings.acitivity.SettingsActivity;
import com.applab.goodmorning.Settings.model.Settings;
import com.applab.goodmorning.Utilities.ItemClickSupport;

import java.util.ArrayList;

public class LanguageActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerview;
    private LanguageAdapter mAdapter;
    private ImageButton mImage;
    private ArrayList<Settings> mSettings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        insertRow();

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.titlte_activity_select_language));
        mImage = (ImageButton) mToolbar.findViewById(R.id.img);
        mImage.setVisibility(View.GONE);

        mRecyclerview = (RecyclerView) findViewById(R.id.myRecyclerView);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(LanguageActivity.this);
        mAdapter = new LanguageAdapter(LanguageActivity.this, mSettings);

        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(mLayoutManager);

        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);
    }

    public void insertRow() {
        String[] language = {getString(R.string.english),
                getString(R.string.malay),
                getString(R.string.chinese)};

        for (int i = 0; i < 3; i++) {
            Settings settings = new Settings();
            settings.setItemSelected(language[i]);
            mSettings.add(settings);
        }
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LanguageActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private ItemClickSupport.OnItemClickListener mItemClickListener = new ItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, final int position, View v) {

        }
    };
}
