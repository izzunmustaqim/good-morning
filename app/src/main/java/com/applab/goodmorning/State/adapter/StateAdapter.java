package com.applab.goodmorning.State.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.R;
import com.applab.goodmorning.State.holder.StateHolder;
import com.applab.goodmorning.State.model.State;

/**
 * Created by user on 31-Mar-16.
 */
public class StateAdapter extends RecyclerView.Adapter<StateHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private Cursor mCursor;

    public StateAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public StateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_state_row, parent, false);
        return new StateHolder(view);
    }

    @Override
    public void onBindViewHolder(StateHolder holder, int position) {
        if (mCursor != null) {
            State state = State.getStateItem(mCursor,position);
            holder.getTxtState().setText(state.getStateName());
            holder.getTxtState().setTag(state);
        }
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    public Cursor swapCursor(Cursor cursor) {
        if (this.mCursor == cursor) {
            return null;
        }
        Cursor oldCursor = this.mCursor;
        this.mCursor = cursor;
        if (cursor != null) {
            notifyDataSetChanged();
        }
        return oldCursor;
    }
}
