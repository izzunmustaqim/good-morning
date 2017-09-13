package com.applab.goodmorning.State.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.applab.goodmorning.R;
import com.applab.goodmorning.State.adapter.StateAdapter;
import com.applab.goodmorning.State.model.State;
import com.applab.goodmorning.State.provider.StateProvider;
import com.applab.goodmorning.State.webapi.HttpHelper;
import com.applab.goodmorning.Utilities.DBHelper;
import com.applab.goodmorning.Utilities.ItemClickSupport;

public class StateActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private ImageButton mImg;
    private String TAG = StateActivity.class.getSimpleName();

    private int mLoaderId = 4243;
    private Cursor mCursor;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerview;
    private StateAdapter mAdapter;

    private State state;

    private int mCountryId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_select_state));

        mImg = (ImageButton) mToolbar.findViewById(R.id.img);
        mImg.setVisibility(View.GONE);

        mRecyclerview = (RecyclerView) findViewById(R.id.myRecyclerView);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(StateActivity.this);
        mAdapter = new StateAdapter(StateActivity.this, mCursor);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(mLayoutManager);

        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);
        mCountryId = getIntent().getIntExtra("countryId", 1);
        ItemClickSupport.addTo(mRecyclerview).setOnItemClickListener(mItemClickListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpHelper.getStateList(StateActivity.this, TAG, mCountryId);
        getSupportLoaderManager().initLoader(mLoaderId, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (mLoaderId == id) {
            return new CursorLoader(StateActivity.this, StateProvider.CONTENT_URI, null, DBHelper.STATE_COLUMN_COUNTRY_ID + "=?", new String[]{String.valueOf(mCountryId)}, null);
        } else {
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (mLoaderId == loader.getId()) {
            if (data != null) {
                mCursor = data;
                mAdapter.swapCursor(data);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    private ItemClickSupport.OnItemClickListener mItemClickListener = new ItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
            TextView txtState = (TextView) v.findViewById(R.id.txtState);
            State state = (State) txtState.getTag();
            Intent intent = new Intent();
            intent.putExtra("StateItem", state);
            setResult(RESULT_OK, intent);
            finish();
        }
    };
}
