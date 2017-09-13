package com.applab.goodmorning.Settings.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.R;
import com.applab.goodmorning.Settings.holder.SettingsHolder;
import com.applab.goodmorning.Settings.model.Settings;

import java.util.ArrayList;

/**
 * Created by user on 09-Mar-16.
 */
public class SettingsAdapter extends RecyclerView.Adapter<SettingsHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    ArrayList<Settings> mSettings = new ArrayList<>();

    public SettingsAdapter(final Context context, final ArrayList<Settings> settingses) {
        mContext = context;
        mSettings = settingses;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public SettingsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.custom_settings_row, parent, false);
        return new SettingsHolder(v);
    }

    @Override
    public void onBindViewHolder(SettingsHolder holder, int position) {
        final SettingsHolder settingsHolder = (SettingsHolder) holder;
        settingsHolder.getTxtSelectedItem().setText(mSettings.get(position).getItemSelected());
        settingsHolder.getTxtSelection().setText(mSettings.get(position).getSelection());
    }

    @Override
    public int getItemCount() {
        int total = mSettings.size();
        return total;
    }
}
