package com.applab.goodmorning.Event.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.applab.goodmorning.Event.activity.EventActivity;
import com.applab.goodmorning.Event.activity.EventDetailsActivity;
import com.applab.goodmorning.Event.adapter.ScheduleAdapter;
import com.applab.goodmorning.Event.decorater.CalenderDecorator;
import com.applab.goodmorning.Event.model.Schedule;
import com.applab.goodmorning.Event.model.ScheduleItems;
import com.applab.goodmorning.Event.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.ItemClickSupport;
import com.applab.goodmorning.Utilities.Utilities;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.Date;

public class MonthFragment extends Fragment implements OnDateSelectedListener, OnMonthChangedListener {
    private MaterialCalendarView calendarView;
    private String TAG = MonthFragment.class.getSimpleName();
    private ScheduleItems mScheduleItems;
    private ScheduleItems mScheduleItemsForSlidingPanel;
    private int mMonth;
    private int mYear;
    private SlidingUpPanelLayout mSlidingUpPanel;
    private RecyclerView mRecyclerView;
    private ScheduleAdapter mAdapter;
    private String mDateFormat = "yyyy-MM-dd";
    private String mTimeStart = "T00:00:00";
    private String mTimeEnd = "T23:59:59";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month, container, false);
        calendarView = (MaterialCalendarView) view.findViewById(R.id.calenderView);
        calendarView.setDateSelected(new Date(), true);
        calendarView.setOnDateChangedListener(this);
        calendarView.setOnMonthChangedListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mAdapter = new ScheduleAdapter(getActivity(), mScheduleItemsForSlidingPanel);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        String month = Utilities.setCalendarDate("M", new Date());
        String year = Utilities.setCalendarDate("yyyy", new Date());
        mMonth = Utilities.getMonth(month);
        mYear = Integer.valueOf(year);

        mSlidingUpPanel = (SlidingUpPanelLayout) view.findViewById(R.id.sliding_layout);
        mSlidingUpPanel.addPanelSlideListener(mPanelSlideListener);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(mItemClickListener);
        return view;
    }

    //region sliding up panel listener region
    private SlidingUpPanelLayout.PanelSlideListener mPanelSlideListener = new SlidingUpPanelLayout.PanelSlideListener() {

        @Override
        public void onPanelSlide(View panel, float slideOffset) {

        }

        @Override
        public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
            Log.i(TAG, "onPanelStateChanged " + newState);
            if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                Intent intent = new Intent(EventActivity.class.getSimpleName());
                intent.putExtra("isSlide", false);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
            } /*else if (newState == SlidingUpPanelLayout.PanelState.DRAGGING) {
                Intent intent = new Intent(EventActivity.class.getSimpleName());
                intent.putExtra("isSlide", true);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
            } */ else if (newState == SlidingUpPanelLayout.PanelState.ANCHORED) {
                mSlidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                Intent intent = new Intent(EventActivity.class.getSimpleName());
                intent.putExtra("isSlide", false);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
            } else if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                Intent intent = new Intent(EventActivity.class.getSimpleName());
                intent.putExtra("isSlide", true);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
            }
        }
    };
    //endregion

    @Override
    public void onResume() {
        super.onResume();
        setMonth(mMonth, mYear);
        IntentFilter iff = new IntentFilter(TAG);
        iff.addAction(EventActivity.class.getSimpleName());
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, iff);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                mScheduleItems = intent.getParcelableExtra("Schedules");
                ArrayList<CalenderDecorator> calenderDecorators = new ArrayList<>();
                for (int i = 0; i < mScheduleItems.getSchedules().size(); i++) {
                    CalenderDecorator calenderDecorator = new CalenderDecorator();
                    calenderDecorator.setCalendar(Utilities.setCalendarDate(Utilities.DATE_FORMAT, mScheduleItems.getSchedules().get(i).getShowDate()));
                    calenderDecorators.add(calenderDecorator);
                }
                calendarView.addDecorators(calenderDecorators);
            } else if (action.equals(EventActivity.class.getSimpleName())) {
                if (!intent.getBooleanExtra("isSlide", false)) {
                    if (mSlidingUpPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
                        mSlidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                    }
                }
                if (intent.getParcelableExtra("Schedules") != null) {
                    mScheduleItemsForSlidingPanel = intent.getParcelableExtra("Schedules");
                    mAdapter.swapSchedule(mScheduleItemsForSlidingPanel);
                    if (mSlidingUpPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
                        mSlidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                    } else {
                        mSlidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                    }
                }
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        for (int i = 0; i < mScheduleItems.getSchedules().size(); i++) {
            if (Utilities.setCalendarDate("yyyy-MM-dd", mDateFormat,
                    mScheduleItems.getSchedules().get(i).getShowDate()).equals
                    (Utilities.setCalendarDate(mDateFormat, date.getDate()))) {
                HttpHelper.getEventList(getActivity(),
                        EventActivity.class.getSimpleName(),
                        Utilities.setCalendarDate(mDateFormat, date.getDate()) + mTimeStart,
                        Utilities.setCalendarDate(mDateFormat, date.getDate()) + mTimeEnd);
                mAdapter.swapSchedule(null);
                break;
            }
        }
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        mMonth = date.getMonth();
        mYear = date.getYear();
        setMonth(mMonth, mYear);
    }

    private void setMonth(Object m, Object y) {
        String startDate = Utilities.setCalendarDate(mDateFormat, Utilities.getFirstDate(m, y)) + mTimeStart;
        String endDate = Utilities.setCalendarDate(mDateFormat, Utilities.getLastDate(m, y)) + mTimeEnd;
        HttpHelper.getMonth(getActivity(), TAG, startDate, endDate);
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
