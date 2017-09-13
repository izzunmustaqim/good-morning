package com.applab.goodmorning.Event.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.Event.holder.ScheduleHolder;
import com.applab.goodmorning.Event.model.Schedule;
import com.applab.goodmorning.Event.model.ScheduleItems;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 4/3/2016.
 */
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleHolder> {
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ScheduleItems mScheduleItems;

    public ScheduleAdapter(Context context, ScheduleItems schedules) {
        mScheduleItems = schedules;
        mContext = context;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ScheduleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_event_row, parent, false);
        return new ScheduleHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleHolder holder, int position) {
        Schedule current = mScheduleItems.getSchedules().get(position);
        holder.getmImgType().setVisibility(View.GONE);
        holder.getmTxtDate().setText(Utilities.setCalendarDate("yyyy-MM-dd", "MMM\ndd", current.getEventFrom()));
        holder.getmTxtDesc().setText(Html.fromHtml(current.getDescription()));
        holder.getmTxtTitle().setText(current.getEventTitle());
        holder.getmTxtTitle().setTag(current);
    }

    @Override
    public int getItemCount() {
        return mScheduleItems == null ? 0 : mScheduleItems.getSchedules().size();
    }

    public void swapSchedule(ScheduleItems scheduleItems) {
        this.mScheduleItems = scheduleItems;
        this.notifyDataSetChanged();
    }
}
