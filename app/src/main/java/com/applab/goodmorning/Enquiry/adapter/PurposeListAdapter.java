package com.applab.goodmorning.Enquiry.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 12/4/2016.
 */
public class PurposeListAdapter extends BaseAdapter {
    private ArrayList<String> mItems = new ArrayList<>();
    private Context context;
    private String mSelectedText;
    private TextView mText1;

    public PurposeListAdapter(ArrayList<String> mItems, Context context) {
        this.mItems = mItems;
        this.context = context;
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
        textView.setText(mItems.get(position));
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
        mText1 = (TextView) convertView.findViewById(android.R.id.text1);
        mText1.setText(mItems.get(position));
        mText1.setTextColor(ContextCompat.getColorStateList(context, R.color.color_primary_text));
        mSelectedText = mItems.get(position);
        return convertView;
    }

    public String getmSelectedText() {
        return mSelectedText;
    }
}
