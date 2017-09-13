package com.applab.goodmorning.Event.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.Event.fragment.ScheduleFragment;
import com.applab.goodmorning.Event.model.Schedule;
import com.applab.goodmorning.Event.model.ScheduleItems;
import com.applab.goodmorning.Event.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class EventDetailsActivity extends AppCompatActivity {
    private TextView mTxtTitle, mTxtDesc, mTxtDate, mTxtLocation, mTxtRemarks, mTxtCreatedBy;
    private ImageView mImgBanner;
    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private Schedule mSchedule;
    private String TAG = EventDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        mToolbar = (Toolbar) findViewById(R.id.appBar);
        setSupportActionBar(mToolbar);
        mSchedule = getIntent().getParcelableExtra("Schedule");
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_events));
        ImageView img = (ImageView) mToolbar.findViewById(R.id.img);
        img.setVisibility(View.GONE);

        mTxtTitle = (TextView) findViewById(R.id.txtName);
        mTxtDesc = (TextView) findViewById(R.id.txtDesc);
        mTxtDate = (TextView) findViewById(R.id.txtDate);
        mTxtLocation = (TextView) findViewById(R.id.txtLocation);
        mTxtRemarks = (TextView) findViewById(R.id.txtRemarks);
        mTxtCreatedBy = (TextView) findViewById(R.id.txtCreatedBy);
        mImgBanner = (ImageView) findViewById(R.id.imgBanner);

        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);
        swapData(mSchedule);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                ScheduleItems scheduleItems = intent.getParcelableExtra("Schedule");
                mSchedule = scheduleItems.getSchedules().get(0);
                swapData(mSchedule);
            }
        }
    };

    private void swapData(Schedule schedule) {
        mTxtTitle.setText(schedule.getEventTitle());
        mTxtDesc.setText(Html.fromHtml(schedule.getDescription()
        ));
        /*String date = Utilities.setCalendarDate("yyyy-MM-dd", "MMM\ndd",
                schedule.getEventFrom()) + " to " + Utilities.setCalendarDate("yyyy-MM-dd", "MMM\ndd", schedule.getEventTo());
        mTxtDate.setText(date);*/
        mTxtDate.setText(schedule.getEventFrom() + " to " + schedule.getEventTo());
        mTxtLocation.setText(schedule.getLocation());
        mTxtRemarks.setText(schedule.getRemarks());
        mTxtCreatedBy.setText(schedule.getCreateBy());
        Glide.with(EventDetailsActivity.this)
                .load(schedule.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.photo)
                .into(mImgBanner);
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
        HttpHelper.getEvent(this, TAG, mSchedule);
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(EventDetailsActivity.this).registerReceiver(broadcastReceiver, iff);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(EventDetailsActivity.this).unregisterReceiver(broadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_event_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
