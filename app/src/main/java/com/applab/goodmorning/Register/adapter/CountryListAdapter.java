package com.applab.goodmorning.Register.adapter;

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
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 29/3/2016
 * -- Description: CountryListAdapter.java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */
public class CountryListAdapter extends BaseAdapter {
    private ArrayList<Country> mItems = new ArrayList<>();
    private Context context;
    private boolean mChoosen = false;
    private Country mCountry;

    public CountryListAdapter(ArrayList<Country> mItems, Context context, boolean isChoosen) {
        this.mItems = mItems;
        this.context = context;
        this.mChoosen = isChoosen;
    }

    public void clear() {
        mItems.clear();
    }

    public void addItem(Country yourObject) {
        mItems.add(yourObject);
    }

    public void addItems(List<Country> yourObjectList) {
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
        Country country = getCountry(position);
        textView.setTag(country);
        mCountry = country;
        textView.setText(country.getCountryName());

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
        Country country = getCountry(position);
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setTag(country);
        textView.setText(country.getCountryName());
        textView.setTextColor(ContextCompat.getColorStateList(context, R.color.color_primary_text));

        return convertView;
    }

    public Country getCountry(int position) {
        return position >= 0 && position < mItems.size() ? mItems.get(position) : null;
    }

    public void swapData(ArrayList<Country> arrayList) {
        mItems = arrayList;
        this.notifyDataSetChanged();
    }
}
