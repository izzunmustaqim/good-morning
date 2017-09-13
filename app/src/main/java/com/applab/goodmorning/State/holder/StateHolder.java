package com.applab.goodmorning.State.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * Created by user on 31-Mar-16.
 */
public class StateHolder extends RecyclerView.ViewHolder {
    private TextView txtState;

    public StateHolder(View itemView) {
        super(itemView);
        txtState = (TextView)itemView.findViewById(R.id.txtState);
    }

    public TextView getTxtState() {
        return txtState;
    }
}
