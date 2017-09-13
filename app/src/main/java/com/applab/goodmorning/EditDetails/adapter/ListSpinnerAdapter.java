package com.applab.goodmorning.EditDetails.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.applab.goodmorning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 17-Mar-16.
 */
public class ListSpinnerAdapter extends BaseAdapter {
    private ArrayList<String> mItems = new ArrayList<>();
    private Context context;
    private boolean mChoosen = false;

    public ListSpinnerAdapter(ArrayList<String> mItems, Context context, boolean isChoosen) {
        this.mItems = mItems;
        this.context = context;
        this.mChoosen = isChoosen;
    }

    public void clear() {
        mItems.clear();
    }

    public void addItem(String yourObject) {
        mItems.add(yourObject);
    }

    public void addItems(List<String> yourObjectList) {
        mItems.addAll(yourObjectList);
    }

    @Override
    public int getCount() {
        return mItems.size();
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
        textView.setText(getTitle(position));

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

        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));
        if (position == 0) {
            textView.setTextColor(ContextCompat.getColorStateList(context, R.color.color_secondary_text));
        } else {
            textView.setTextColor(ContextCompat.getColorStateList(context, R.color.color_primary_text));
        }

        return convertView;
    }

    private String getTitle(int position) {
        return position >= 0 && position < mItems.size() ? mItems.get(position) : "";
    }
}
