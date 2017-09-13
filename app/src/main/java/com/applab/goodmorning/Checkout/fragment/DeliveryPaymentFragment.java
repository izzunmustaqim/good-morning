package com.applab.goodmorning.Checkout.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.applab.goodmorning.Account.model.Account;
import com.applab.goodmorning.Account.provider.AccountProvider;
import com.applab.goodmorning.Checkout.activity.CartActivity;
import com.applab.goodmorning.Checkout.model.CheckOut;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.adapter.CountryListAdapter;
import com.applab.goodmorning.Register.adapter.StateListAdapter;
import com.applab.goodmorning.Register.model.Country;
import com.applab.goodmorning.Register.provider.CountryProvider;
import com.applab.goodmorning.State.activity.StateActivity;
import com.applab.goodmorning.State.model.State;
import com.applab.goodmorning.State.provider.StateProvider;
import com.applab.goodmorning.State.webapi.HttpHelper;
import com.applab.goodmorning.Utilities.DBHelper;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Utilities.dialog.GeneralDialogFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DeliveryPaymentFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private EditText mEdiFirstName;
    private EditText mEdiLastName;
    private EditText mEdiPhone;
    private EditText mEdiEmail;
    private EditText mEdiAddress;
    private EditText mEdiCity;
    private EditText mEdiPostCode;
    private Spinner mSpinnerState;
    private EditText mEdiRemark;
    private Button mBtnContinue;
    private LinearLayout mBtnDefault;
    private ImageView mImgCheck;
    private int mPosition = 1;
    private int mRequestCode = 4584;
    private CountryListAdapter mAdapter;
    private Spinner mSpinner;
    private int mIdCountry = 468;
    private int mIdAccount = 5498;
    private int mIdState = 598;
    private ArrayList<Country> mCountrys = new ArrayList<>();
    private Account mAccount;
    private TextView mTxtTitle;
    private View mShippingInfo;
    private ImageView mImgPaypal;
    private ImageView mImgBankTransfer;
    private LinearLayout mBtnPaypal;
    private LinearLayout mBtnBankTransfer;
    private boolean mIsDelivery = false;
    private CardView mCardViewPayment;
    private CardView mCardViewRemarks;
    public String mStateCode = null;
    public Integer mCountryId = 1;
    private String TAG = DeliveryPaymentFragment.class.getSimpleName();
    private StateListAdapter mAdapterState;


    public static DeliveryPaymentFragment newInstance(final boolean isDelivery) {
        DeliveryPaymentFragment frag = new DeliveryPaymentFragment();
        Bundle args = new Bundle();
        args.putBoolean("isDelivery", isDelivery);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mIsDelivery = getArguments().getBoolean("isDelivery");
        View view = inflater.inflate(R.layout.fragment_delivery_payment, container, false);
        mShippingInfo = view.findViewById(R.id.shipping_info);
        mImgPaypal = (ImageView) view.findViewById(R.id.imgPaypal);
        mImgPaypal.setTag(true);
        mImgPaypal.setImageResource(R.mipmap.radio_on);
        mImgBankTransfer = (ImageView) view.findViewById(R.id.imgBankTransfer);
        mImgBankTransfer.setTag(false);
        mImgBankTransfer.setImageResource(R.mipmap.radio_off);
        mBtnPaypal = (LinearLayout) view.findViewById(R.id.btnPaypal);
        mBtnBankTransfer = (LinearLayout) view.findViewById(R.id.btnBankTransfer);
        mCardViewPayment = (CardView) view.findViewById(R.id.cardViewPayment);
        mCardViewRemarks = (CardView) view.findViewById(R.id.cardViewRemarks);

        mEdiFirstName = (EditText) mShippingInfo.findViewById(R.id.ediFirstName);
        mEdiLastName = (EditText) mShippingInfo.findViewById(R.id.ediLastName);
        mEdiPhone = (EditText) mShippingInfo.findViewById(R.id.ediPhone);
        mEdiEmail = (EditText) mShippingInfo.findViewById(R.id.ediEmail);
        mEdiAddress = (EditText) mShippingInfo.findViewById(R.id.ediAddress);
        mEdiCity = (EditText) mShippingInfo.findViewById(R.id.ediCity);
        mEdiPostCode = (EditText) mShippingInfo.findViewById(R.id.ediPostCode);
        mSpinnerState = (Spinner) mShippingInfo.findViewById(R.id.spinner_state);
        mEdiRemark = (EditText) view.findViewById(R.id.ediRemark);
        mBtnContinue = (Button) view.findViewById(R.id.btnContinue);
        mBtnDefault = (LinearLayout) mShippingInfo.findViewById(R.id.btnDefault);
        mImgCheck = (ImageView) mShippingInfo.findViewById(R.id.imgCheck);
        mTxtTitle = (TextView) mShippingInfo.findViewById(R.id.txtTitle);
        mAdapter = new CountryListAdapter(mCountrys, getActivity(), false);
        mSpinner = (Spinner) mShippingInfo.findViewById(R.id.spinner);
        mSpinner.setAdapter(mAdapter);
        mImgCheck.setVisibility(View.GONE);
        mImgCheck.setTag(false);
        mBtnDefault.setOnClickListener(mBtnDefaultOnClickListener);
        mBtnContinue.setOnClickListener(mBtnContinueOnClickListener);
        mAdapterState = new StateListAdapter(null, getActivity(), false);
        mSpinnerState.setAdapter(mAdapterState);
        mBtnBankTransfer.setOnClickListener(mBtnBankTransferOnClickListener);
        mBtnPaypal.setOnClickListener(mBtnPaypalOnClickListener);

        if (!mIsDelivery) {
            mPosition = 2;
            mCardViewPayment.setVisibility(View.VISIBLE);
            mCardViewRemarks.setVisibility(View.GONE);
            mTxtTitle.setText(getString(R.string.billing_info));
        } else {
            mPosition = 1;
            mCardViewPayment.setVisibility(View.GONE);
            mCardViewRemarks.setVisibility(View.VISIBLE);
            mTxtTitle.setText(getString(R.string.shipment_info));
        }
        mSpinner.setOnItemSelectedListener(mSpinnerOnItemSelectedListener);
        return view;
    }

    private Spinner.OnItemSelectedListener mSpinnerOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mCountryId = mAdapter.getCountry(position).getId();
            com.applab.goodmorning.State.webapi.HttpHelper.getStateList(getActivity(), TAG, mCountryId);
            getActivity().getSupportLoaderManager().restartLoader(mIdState, null, DeliveryPaymentFragment.this);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private View.OnClickListener mBtnPaypalOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!(boolean) mImgPaypal.getTag()) {
                mImgPaypal.setTag(true);
                mImgPaypal.setImageResource(R.mipmap.radio_on);
                mImgBankTransfer.setTag(false);
                mImgBankTransfer.setImageResource(R.mipmap.radio_off);
            }
        }
    };

    private View.OnClickListener mBtnBankTransferOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!(boolean) mImgBankTransfer.getTag()) {
                mImgBankTransfer.setTag(true);
                mImgBankTransfer.setImageResource(R.mipmap.radio_on);
                mImgPaypal.setTag(false);
                mImgPaypal.setImageResource(R.mipmap.radio_off);
            }
        }
    };

    private String checkValidation() {
        String message = null;
        if (mEdiEmail.getText().toString().length() == 0) {
            message = getString(R.string.email_error);
        } else if (mEdiFirstName.getText().toString().length() == 0) {
            message = getString(R.string.first_name_error);
        } else if (mEdiLastName.getText().toString().length() == 0) {
            message = getString(R.string.last_name_error);
        } else if (mEdiPhone.getText().toString().length() == 0) {
            message = getString(R.string.phone_error);
        } else if (!Utilities.isValidEmail(mEdiEmail.getText().toString())) {
            message = getString(R.string.email_not_valid);
        } else if (mEdiAddress.getText().toString().length() == 0) {
            message = getString(R.string.address_error);
        } else if (mEdiCity.getText().toString().length() == 0) {
            message = getString(R.string.city_error);
        } else if (mEdiPostCode.getText().toString().length() == 0) {
            message = getString(R.string.postcode_error);
        }
        return message;
    }

    private void clearEditText(boolean isRemarkEmpty) {
        mEdiFirstName.setText("");
        mEdiLastName.setText("");
        mEdiPhone.setText("");
        mEdiEmail.setText("");
        mEdiAddress.setText("");
        mEdiCity.setText("");
        mEdiPostCode.setText("");
        if (isRemarkEmpty) {
            mEdiRemark.setText("");
        }
    }

    private View.OnClickListener mBtnDefaultOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if ((boolean) mImgCheck.getTag()) {
                mImgCheck.setTag(false);
                mImgCheck.setVisibility(View.GONE);
                clearEditText(false);
                getActivity().getSupportLoaderManager().restartLoader(mIdAccount, null, DeliveryPaymentFragment.this);
                getActivity().getSupportLoaderManager().restartLoader(mIdState, null, DeliveryPaymentFragment.this);
            } else {
                mImgCheck.setTag(true);
                getActivity().getSupportLoaderManager().restartLoader(mIdAccount, null, DeliveryPaymentFragment.this);
                getActivity().getSupportLoaderManager().restartLoader(mIdState, null, DeliveryPaymentFragment.this);
                mImgCheck.setVisibility(View.VISIBLE);
            }
        }
    };

    private View.OnClickListener mBtnContinueOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CartActivity.class.getSimpleName());
            intent.putExtra("isSlide", true);
            intent.putExtra("position", mPosition + 1);
            if (mIsDelivery) {
                if (checkValidation() == null) {
                    CheckOut checkOut = new Gson().fromJson(Utilities.getSharePreference("CheckOutFile", "CheckOutValue", getActivity()), CheckOut.class);
                    checkOut.setContactNo(mEdiPhone.getText().toString());
                    checkOut.setAddress(mEdiAddress.getText().toString());
                    checkOut.setCity(mEdiCity.getText().toString());
                    checkOut.setZipCode(mEdiPostCode.getText().toString());
                    if (mAdapterState.getCount() != 0) {
                        checkOut.setStateCode(mAdapterState.getState(mSpinnerState.getSelectedItemPosition()).getStateCode());
                    }
                    checkOut.setCountryId(String.valueOf(mAdapter.getCountry(mSpinner.getSelectedItemPosition()).getId()));
                    checkOut.setEmail(mEdiEmail.getText().toString());
                    checkOut.setFirstName(mEdiFirstName.getText().toString());
                    checkOut.setLastName(mEdiLastName.getText().toString());
                    checkOut.setRemarks(mEdiRemark.getText().toString());
                    Utilities.setSharePreference(new Gson().toJson(checkOut), "CheckOutFile", "CheckOutValue", getActivity());
                    LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                } else {
                    GeneralDialogFragment generalDialogFragment = GeneralDialogFragment.newInstance(getActivity(), checkValidation(), getString(R.string.warning));
                    generalDialogFragment.show(getActivity().getSupportFragmentManager(), TAG);
                }
            } else {
                if (checkValidation() != null) {
                    GeneralDialogFragment generalDialogFragment = GeneralDialogFragment.newInstance(getActivity(), checkValidation(), getString(R.string.warning));
                    generalDialogFragment.show(getActivity().getSupportFragmentManager(), TAG);
                } else {
                    LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                }
            }
        }
    };

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == mIdAccount) {
            return new CursorLoader(getActivity(), AccountProvider.CONTENT_URI, null, null, null, null);
        } else if (id == mIdCountry) {
            return new CursorLoader(getActivity(), CountryProvider.CONTENT_URI, null, null, null, null);
        } else if (id == mIdState) {
            return new CursorLoader(getActivity(), StateProvider.CONTENT_URI, null, DBHelper.STATE_COLUMN_COUNTRY_ID + "=?", new String[]{String.valueOf(mCountryId)}, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (mIdAccount == loader.getId()) {
            if (data != null) {
                if (data.getCount() > 0) {
                    if ((Boolean) mImgCheck.getTag()) {
                        mAccount = Account.getAccount(data, 0);
                        mEdiFirstName.setText(mAccount.getFirstName());
                        mEdiLastName.setText(mAccount.getLastName());
                        mEdiPhone.setText(mAccount.getContactNo());
                        mEdiEmail.setText(mAccount.getEmail());
                        mEdiAddress.setText(mAccount.getDefAddress());
                        mEdiCity.setText(mAccount.getDefCity());
                        mStateCode = mAccount.getDefStateCode();
                        mEdiPostCode.setText(mAccount.getDefZipcode());
                        for (int j = 0; j < mCountrys.size(); j++) {
                            if (mCountrys.get(j).getId().equals(mAccount.getCountryId())) {
                                mSpinner.setSelection(j);
                                mCountryId = mCountrys.get(j).getId();
                                com.applab.goodmorning.State.webapi.HttpHelper.getStateList(getActivity(), TAG, mCountryId);
                            }
                        }
                        if (mAdapterState.getCount() != 0) {
                            for (int i = 0; i < mAdapterState.getCount(); i++) {
                                if (mAccount.getDefStateCode().equals(mAdapterState.getState(i).getStateCode()))
                                    mSpinnerState.setSelection(i);
                            }
                        }
                    }
                }
            }
        } else if (mIdCountry == loader.getId()) {
            mCountrys.clear();
            if (data != null) {
                if (data.getCount() > 0) {
                    for (int i = 0; i < data.getCount(); i++) {
                        mCountrys.add(Country.getCountry(data, i));
                    }
                    mAdapter.swapData(mCountrys);
                    mCountryId = mAdapter.getCountry(0).getId();
                    com.applab.goodmorning.State.webapi.HttpHelper.getStateList(getActivity(), TAG, mCountryId);
                }
            }
        } else if (mIdState == loader.getId()) {
            if (data != null) {
                if (data.getCount() > 0) {
                    mSpinnerState.setVisibility(View.VISIBLE);
                    ArrayList<State> arrayList = new ArrayList<>();
                    for (int i = 0; i < data.getCount(); i++) {
                        State state = State.getStateItem(data, i);
                        arrayList.add(state);
                    }
                    mAdapterState.swapData(arrayList);
                    if ((boolean) mImgCheck.getTag()) {
                        if (mAccount != null) {
                            for (int i = 0; i < data.getCount(); i++) {
                                State state = State.getStateItem(data, i);
                                if (mAccount.getDefStateCode().equals(state.getStateCode()))
                                    mSpinnerState.setSelection(i);
                            }

                        }
                    }
                } else {
                    mAdapterState.swapData(null);
                    mSpinnerState.setVisibility(View.INVISIBLE);
                }
            } else {
                mAdapterState.swapData(null);
                mSpinnerState.setVisibility(View.INVISIBLE);

            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getSupportLoaderManager().initLoader(mIdAccount, null, DeliveryPaymentFragment.this);
        getActivity().getSupportLoaderManager().initLoader(mIdCountry, null, DeliveryPaymentFragment.this);
        getActivity().getSupportLoaderManager().initLoader(mIdState, null, DeliveryPaymentFragment.this);
        com.applab.goodmorning.Account.webapi.HttpHelper.getMyProfileApi(getActivity(), TAG);
        com.applab.goodmorning.Register.webapi.HttpHelper.getCountry(getActivity(), TAG);
        HttpHelper.getStateList(getActivity(), TAG, mCountryId);

    }
}
