package com.applab.goodmorning.Settings.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * Created by user on 09-Mar-16.
 */
public class SettingsHolder extends RecyclerView.ViewHolder {
    private TextView txtSelection;
    private TextView txtSelectedItem;

    public SettingsHolder(View itemView) {
        super(itemView);
        txtSelection = (TextView) itemView.findViewById(R.id.txtSelection);
        txtSelectedItem = (TextView) itemView.findViewById(R.id.txtSelectionItem);
    }

    public TextView getTxtSelection() {
        return txtSelection;
    }

    public TextView getTxtSelectedItem() {
        return txtSelectedItem;
    }
}
