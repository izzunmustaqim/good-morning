package com.applab.goodmorning.Event.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applab.goodmorning.Event.activity.EventDetailsActivity;
import com.applab.goodmorning.Event.adapter.ScheduleAdapter;
import com.applab.goodmorning.Event.model.Schedule;
import com.applab.goodmorning.Event.model.ScheduleItems;
import com.applab.goodmorning.Event.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.ItemClickSupport;
import com.applab.goodmorning.Utilities.Utilities;

import java.util.Date;

public class ScheduleFragment extends Fragment {
    private ScheduleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private static final String TAG = ScheduleFragment.class.getSimpleName();
    private ScheduleItems mScheduleItems;
    private String mDateFormat = "yyyy-MM-dd";
    private String mTimeStart = "T00:00:00";
    private String mTimeEnd = "T23:59:59";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ScheduleAdapter(getActivity(), mScheduleItems);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(mItemClickListener);
        return view;
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                mScheduleItems = intent.getParcelableExtra("Schedules");
                mAdapter.swapSchedule(mScheduleItems);
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        Object month = Utilities.setCalendarDate("M", new Date());
        Object year = Utilities.setCalendarDate("yyyy", new Date());
        final String startDate = Utilities.setCalendarDate(mDateFormat, Utilities.getFirstDate(Utilities.getMonth(month), year));
        final String endDate = Utilities.setCalendarDate(mDateFormat, Utilities.getLastDate(Utilities.getMonth(month), year));
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                HttpHelper.getEventList(getActivity(), TAG, startDate, endDate);
            }
        };
        handler.postDelayed(r, 100);

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
            TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
            Schedule schedule = (Schedule) txtTitle.getTag();
            Intent intent = new Intent(getActivity(), EventDetailsActivity.class);
            intent.putExtra("Schedule", schedule);
            startActivity(intent);
        }
    };
}
