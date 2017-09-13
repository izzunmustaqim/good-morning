package com.applab.goodmorning.ContactUs.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.ContactUs.adapter.ContactUsAdapter;
import com.applab.goodmorning.ContactUs.model.ContactUs;
import com.applab.goodmorning.ContactUs.model.ContactUsItem;
import com.applab.goodmorning.ContactUs.webapi.HttpHelper;
import com.applab.goodmorning.R;

public class ContactUsFragment extends Fragment {
    private static final String POSITION = "position";
    private int mPosition = 0;
    private RecyclerView mRecyclerView;
    private ContactUsAdapter mAdapter;
    private String TAG = ContactUsFragment.class.getSimpleName() + mPosition;
    private ContactUsItem mContactUsItem;

    public static ContactUsFragment newInstance(int position) {
        ContactUsFragment fragment = new ContactUsFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPosition = getArguments().getInt(POSITION);
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mAdapter = new ContactUsAdapter(getActivity(), mContactUsItem);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        TAG = ContactUsFragment.class.getSimpleName() + mPosition;
        HttpHelper.getOutlet(getActivity(), TAG, mPosition);
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, iff);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                mContactUsItem = intent.getParcelableExtra("Outlets");
                mAdapter.swapContactUs(mContactUsItem);
            }
        }
    };
}
