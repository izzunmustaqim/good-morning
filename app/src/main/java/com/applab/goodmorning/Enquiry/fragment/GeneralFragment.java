package com.applab.goodmorning.Enquiry.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.applab.goodmorning.Enquiry.activity.EnquiryActivity;
import com.applab.goodmorning.Enquiry.adapter.PurposeListAdapter;
import com.applab.goodmorning.Enquiry.model.Enquiry;
import com.applab.goodmorning.Enquiry.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Utilities.dialog.GeneralDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneralFragment extends Fragment {
    private EditText mEdiName;
    private EditText mEdiEmail;
    private EditText mEdiPhone;
    private EditText mEdiMessage;
    private RelativeLayout mBtnSend;
    private PurposeListAdapter mAdapter;
    private String TAG = GeneralFragment.class.getSimpleName();
    private Spinner mSpinner;
    private RelativeLayout mRlMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general, container, false);
        mEdiEmail = (EditText) view.findViewById(R.id.ediEmail);
        mEdiName = (EditText) view.findViewById(R.id.ediName);
        mEdiMessage = (EditText) view.findViewById(R.id.ediMessage);
        mEdiPhone = (EditText) view.findViewById(R.id.ediPhone);
        mRlMessage = (RelativeLayout) view.findViewById(R.id.rlMessage);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        mBtnSend = (RelativeLayout) view.findViewById(R.id.btnSend);
        mAdapter = new PurposeListAdapter(new ArrayList<String>(Arrays.asList(getActivity().getResources().getStringArray(R.array.purpose))), getActivity());
        mSpinner.setAdapter(mAdapter);
        mBtnSend.setOnClickListener(mBtnSendOnClickListener);
        mRlMessage.setOnClickListener(mRlMessageOnClickListener);
        return view;
    }

    private View.OnClickListener mRlMessageOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utilities.showKeyboard(getActivity(), mEdiMessage);
        }
    };

    private String checkValidation() {
        String message = null;
        if (mEdiEmail.getText().toString().length() == 0) {
            message = getString(R.string.email_error);
        } else if (mEdiName.getText().toString().length() == 0) {
            message = getString(R.string.name_error);
        } else if (mEdiMessage.getText().toString().length() == 0) {
            message = getString(R.string.message_error);
        } else if (mEdiPhone.getText().toString().length() == 0) {
            message = getString(R.string.phone_error);
        } else if (!Utilities.isValidEmail(mEdiEmail.getText().toString())) {
            message = getString(R.string.email_not_valid);
        }
        return message;
    }

    private View.OnClickListener mBtnSendOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkValidation() == null) {
                Enquiry enquiry = new Enquiry();
                enquiry.setContactNo(mEdiPhone.getText().toString());
                enquiry.setEmail(mEdiEmail.getText().toString());
                enquiry.setMessage(mEdiMessage.getText().toString());
                enquiry.setName(mEdiName.getText().toString());
                enquiry.setPurpose(mAdapter.getmSelectedText());
                HttpHelper.postEnquiry(getActivity(), EnquiryActivity.class.getSimpleName(), enquiry, "Enquiry/General");
            } else {
                GeneralDialogFragment generalDialogFragment = GeneralDialogFragment.newInstance(getActivity(), checkValidation(), getString(R.string.warning));
                generalDialogFragment.show(getActivity().getSupportFragmentManager(), TAG);
            }
        }
    };

    private void clearEditText() {
        mEdiEmail.setText("");
        mEdiName.setText("");
        mEdiMessage.setText("");
        mEdiPhone.setText("");
        mSpinner.setSelection(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, iff);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                if (intent.getBooleanExtra("success", true)) {
                    clearEditText();
                }
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        Utilities.hideKeyboard(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }
}
