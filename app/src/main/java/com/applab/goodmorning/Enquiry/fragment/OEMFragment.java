package com.applab.goodmorning.Enquiry.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.applab.goodmorning.Enquiry.activity.EnquiryActivity;
import com.applab.goodmorning.Enquiry.model.Enquiry;
import com.applab.goodmorning.Enquiry.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Utilities.dialog.GeneralDialogFragment;

public class OEMFragment extends Fragment {
    private EditText mEdiFirstName;
    private EditText mEdiLastName;
    private EditText mEdiCompanyName;
    private EditText mEdiAddress;
    private EditText mEdiSubject;
    private EditText mEdiMessage;
    private RelativeLayout mBtnSend;
    private String TAG = OEMFragment.class.getSimpleName();
    private RelativeLayout mRlMessage;
    private RelativeLayout mRlCompanyName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oem, container, false);
        mEdiFirstName = (EditText) view.findViewById(R.id.ediFirstName);
        mEdiLastName = (EditText) view.findViewById(R.id.ediLastName);
        mEdiCompanyName = (EditText) view.findViewById(R.id.ediCompanyName);
        mEdiSubject = (EditText) view.findViewById(R.id.ediSubject);
        mEdiAddress = (EditText) view.findViewById(R.id.ediAddress);
        mEdiMessage = (EditText) view.findViewById(R.id.ediMessage);
        mBtnSend = (RelativeLayout) view.findViewById(R.id.btnSend);
        mBtnSend.setOnClickListener(mBtnSendOnClickListener);
        mRlMessage = (RelativeLayout) view.findViewById(R.id.rlMessage);
        mRlCompanyName = (RelativeLayout) view.findViewById(R.id.rlCompanyName);
        mRlMessage.setOnClickListener(mRlMessageOnClickListener);
        mRlCompanyName.setOnClickListener(mRlCompanyNameOnClickListener);
        return view;
    }

    private View.OnClickListener mRlMessageOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utilities.showKeyboard(getActivity(), mEdiMessage);
        }
    };

    private View.OnClickListener mRlCompanyNameOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utilities.showKeyboard(getActivity(), mEdiCompanyName);
        }
    };

    private String checkValidation() {
        String message = null;
        if (mEdiFirstName.getText().toString().length() == 0) {
            message = getString(R.string.first_name_error);
        } else if (mEdiLastName.getText().toString().length() == 0) {
            message = getString(R.string.last_name_error);
        } else if (mEdiCompanyName.getText().toString().length() == 0) {
            message = getString(R.string.company_name_error);
        } else if (mEdiSubject.getText().toString().length() == 0) {
            message = getString(R.string.subject_error);
        } else if (mEdiAddress.getText().toString().length() == 0) {
            message = getString(R.string.address_error);
        } else if (mEdiMessage.getText().toString().length() == 0) {
            message = getString(R.string.message_error);
        }
        return message;
    }

    private View.OnClickListener mBtnSendOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkValidation() == null) {
                Enquiry enquiry = new Enquiry();
                enquiry.setFirstName(mEdiFirstName.getText().toString());
                enquiry.setLastName(mEdiLastName.getText().toString());
                enquiry.setCompanyName(mEdiCompanyName.getText().toString());
                enquiry.setSubject(mEdiSubject.getText().toString());
                enquiry.setAddress(mEdiAddress.getText().toString());
                enquiry.setMessage(mEdiMessage.getText().toString());
                enquiry.setMessage(mEdiMessage.getText().toString());
                HttpHelper.postEnquiry(getActivity(), TAG, enquiry, "Enquiry/OEM");
            } else {
                GeneralDialogFragment generalDialogFragment = GeneralDialogFragment.newInstance(getActivity(), checkValidation(), getString(R.string.warning));
                generalDialogFragment.show(getActivity().getSupportFragmentManager(), TAG);
            }
        }
    };

    private void clearEditText() {
        mEdiFirstName.setText("");
        mEdiLastName.setText("");
        mEdiCompanyName.setText("");
        mEdiSubject.setText("");
        mEdiMessage.setText("");
        mEdiAddress.setText("");
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
