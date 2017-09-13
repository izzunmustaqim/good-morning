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

public class RetailDistributorFragment extends Fragment {
    private EditText mEdiName;
    private EditText mEdiNameOutlet;
    private EditText mEdiAddress;
    private EditText mEdiPostCode;
    private EditText mEdiEmail;
    private EditText mEdiPhone;
    private EditText mEdiMessage;
    private RelativeLayout mBtnSend;
    private String TAG = OEMFragment.class.getSimpleName();
    private RelativeLayout mRlMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retail_distributor, container, false);
        mEdiName = (EditText) view.findViewById(R.id.ediName);
        mEdiNameOutlet = (EditText) view.findViewById(R.id.ediNameOutlet);
        mEdiPostCode = (EditText) view.findViewById(R.id.ediPostCode);
        mEdiEmail = (EditText) view.findViewById(R.id.ediEmail);
        mEdiPhone = (EditText) view.findViewById(R.id.ediPhone);
        mEdiAddress = (EditText) view.findViewById(R.id.ediAddress);
        mEdiMessage = (EditText) view.findViewById(R.id.ediMessage);
        mBtnSend = (RelativeLayout) view.findViewById(R.id.btnSend);
        mRlMessage = (RelativeLayout) view.findViewById(R.id.rlMessage);
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
        if (mEdiName.getText().toString().length() == 0) {
            message = getString(R.string.name_error);
        } else if (mEdiNameOutlet.getText().toString().length() == 0) {
            message = getString(R.string.name_outlet_error);
        } else if (mEdiEmail.getText().toString().length() == 0) {
            message = getString(R.string.email_error);
        } else if (mEdiPhone.getText().toString().length() == 0) {
            message = getString(R.string.phone_error);
        } else if (mEdiAddress.getText().toString().length() == 0) {
            message = getString(R.string.address_outlet_error);
        } else if (mEdiMessage.getText().toString().length() == 0) {
            message = getString(R.string.message_error);
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
                enquiry.setName(mEdiName.getText().toString());
                enquiry.setOutletName(mEdiNameOutlet.getText().toString());
                enquiry.setOutletZipcode(mEdiPostCode.getText().toString());
                enquiry.setEmail(mEdiEmail.getText().toString());
                enquiry.setContactNo(mEdiPhone.getText().toString());
                enquiry.setOutletAddress(mEdiAddress.getText().toString());
                enquiry.setMessage(mEdiMessage.getText().toString());
                HttpHelper.postEnquiry(getActivity(), TAG, enquiry, "Enquiry/RnD");
            } else {
                GeneralDialogFragment generalDialogFragment = GeneralDialogFragment.newInstance(getActivity(), checkValidation(), getString(R.string.warning));
                generalDialogFragment.show(getActivity().getSupportFragmentManager(), TAG);
            }
        }
    };

    private void clearEditText() {
        mEdiName.setText("");
        mEdiNameOutlet.setText("");
        mEdiPostCode.setText("");
        mEdiEmail.setText("");
        mEdiPhone.setText("");
        mEdiAddress.setText("");
        mEdiMessage.setText("");
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
