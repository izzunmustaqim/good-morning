package com.applab.goodmorning.Register.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.applab.goodmorning.R;
import com.applab.goodmorning.State.model.State;
import com.applab.goodmorning.State.model.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lau on 16/04/2016.
 */
public class StateListAdapter extends BaseAdapter {
    private ArrayList<State> mItems = new ArrayList<>();
    private Context context;
    private boolean mChoosen = false;
    private State mState;

    public StateListAdapter(ArrayList<State> mItems, Context context, boolean isChoosen) {
        this.mItems = mItems;
        this.context = context;
        this.mChoosen = isChoosen;
    }

    public void clear() {
        mItems.clear();
    }

    public void addItem(State yourObject) {
        mItems.add(yourObject);
    }

    public void addItems(List<State> yourObjectList) {
        mItems.addAll(yourObjectList);
    }

    @Override
    public int getCount() {
        return mItems == null?0: mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !convertView.getTag().toString().equals("DROPDOWN")) {
            LayoutInflater inflater = null;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_spinner_item, parent, false);
            convertView.setTag("DROPDOWN");
        }

        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        State state = getState(position);
        textView.setTag(state);
        mState = state;
        textView.setText(state.getStateName());
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !convertView.getTag().toString().equals("NON_DROPDOWN")) {
            LayoutInflater inflater = null;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_spinner, parent, false);
            convertView.setTag("NON_DROPDOWN");
        }
        State state = getState(position);
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setTag(state);
        textView.setText(state.getStateName());
        textView.setTextColor(ContextCompat.getColorStateList(context, R.color.color_primary_text));

        return convertView;
    }

    public State getState(int position) {
        return position >= 0 && position < mItems.size() ? mItems.get(position) : null;
    }

    public void swapData(ArrayList<State> arrayList) {
        mItems = arrayList;
        this.notifyDataSetChanged();
    }
}
