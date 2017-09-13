package com.applab.goodmorning.Register.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.adapter.CountryListAdapter;
import com.applab.goodmorning.Register.adapter.StateListAdapter;
import com.applab.goodmorning.Register.model.Country;
import com.applab.goodmorning.Register.model.Register;
import com.applab.goodmorning.Register.provider.CountryProvider;
import com.applab.goodmorning.Register.webapi.HttpHelper;
import com.applab.goodmorning.State.activity.StateActivity;
import com.applab.goodmorning.State.model.State;
import com.applab.goodmorning.State.provider.StateProvider;
import com.applab.goodmorning.Utilities.DBHelper;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Utilities.dialog.GeneralDialogFragment;

import java.util.ArrayList;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 7/3/2016
 * -- Description: RegisterActivity.java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class RegisterActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private ImageButton mImg;
    private CountryListAdapter mSpinnerAdapter;
    private Spinner mSpinner;
    private LinearLayout mCboCheck;
    private ImageView mImgCheck;
    private Button btnRegister;
    private EditText etUsername, etEmail, etPassword, etConfirmPassword;
    private EditText etSalutation, etFirstName, etLastName, etPhone, etStreetAddress, etCity, etZipcode, etState;
    private int pressed = 0;
    private String TAG = RegisterActivity.class.getSimpleName();
    private RelativeLayout fadeProgressBar;
    private ProgressBar progressBar;
    private int mLoaderId = 4658;
    private CardView cardShippingAddress;
    private LinearLayout defShippingAddress;
    private Spinner mSpineerState, mSpinnerDefState;
    private int mRequestCode = 222;

    private Spinner mDefSpinner;
    private int mRequestCodeDef = 333;
    private EditText mEtDefAddress;
    private EditText mEtDefZipCode;
    private EditText mEtDefCity;
    private EditText mEtDefCountryId;
    private int mCountryId = 1;
    private int mDefCountryId = 1;
    private int mIDState = 4245;
    private int mIDDefState = 4425;

    private StateListAdapter mStateAdapter;
    private StateListAdapter mStateDefAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etSalutation = (EditText) findViewById(R.id.etSalutation);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPhone = (EditText) findViewById(R.id.etPhone);

        etStreetAddress = (EditText) findViewById(R.id.etStreetAddress);
        etCity = (EditText) findViewById(R.id.etCity);
        etZipcode = (EditText) findViewById(R.id.etZipCode);
        cardShippingAddress = (CardView) findViewById(R.id.cardShippingAddress);
        defShippingAddress = (LinearLayout) findViewById(R.id.defShippingAddress);
        mSpineerState = (Spinner) findViewById(R.id.spinner_state);
        mSpinnerDefState = (Spinner) findViewById(R.id.spinner_def_state);

        mStateAdapter = new StateListAdapter(null, this, false);
        mStateDefAdapter = new StateListAdapter(null, this, false);
        mSpineerState.setAdapter(mStateAdapter);
        mSpinnerDefState.setAdapter(mStateDefAdapter);


        mEtDefAddress = (EditText) findViewById(R.id.etDefAddress);
        mEtDefZipCode = (EditText) findViewById(R.id.etDefZipCode);
        mEtDefCity = (EditText) findViewById(R.id.etDefCity);

        fadeProgressBar = (RelativeLayout) findViewById(R.id.fadeProgressBar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Utilities.setFadeProgressBarVisibility(false, fadeProgressBar, progressBar);

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mToolbar.setFitsSystemWindows(false);
        mToolbar.setPadding(0, 0, 0, 0);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_register));

        mImg = (ImageButton) mToolbar.findViewById(R.id.img);
        mImg.setVisibility(View.GONE);

        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);

        mSpinnerAdapter = new CountryListAdapter(new ArrayList<Country>(), this, false);
        mSpinner = (Spinner) findViewById(R.id.spinner_country);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(mSpinnerItemListener);

        mDefSpinner = (Spinner) findViewById(R.id.def_spinner_country);
        mDefSpinner.setAdapter(mSpinnerAdapter);
        mDefSpinner.setOnItemSelectedListener(mDefSpinnerItemListener);

        mCboCheck = (LinearLayout) findViewById(R.id.cboCheck);
        mImgCheck = (ImageView) findViewById(R.id.imgCheck);

        mCboCheck.setOnClickListener(mCboCheckOnClickListener);
        btnRegister.setOnClickListener(btnRegisterOnClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener mCboCheckOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (pressed == 0) {
                mImgCheck.setImageResource(R.drawable.check_1_after);
                pressed = 1;
                cardShippingAddress.setVisibility(View.GONE);
                //defShippingAddress.setVisibility(View.GONE);
            } else {
                mImgCheck.setImageResource(R.drawable.check_1_before);
                pressed = 0;
                cardShippingAddress.setVisibility(View.VISIBLE);
                //defShippingAddress.setVisibility(View.VISIBLE);
            }
        }
    };

    private Spinner.OnItemSelectedListener mSpinnerItemListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mCountryId = mSpinnerAdapter.getCountry(position).getId();
            com.applab.goodmorning.State.webapi.HttpHelper.getStateList(RegisterActivity.this,TAG,mCountryId);
            getSupportLoaderManager().restartLoader(mIDState, null, RegisterActivity.this);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private Spinner.OnItemSelectedListener mDefSpinnerItemListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mDefCountryId = mSpinnerAdapter.getCountry(position).getId();
            com.applab.goodmorning.State.webapi.HttpHelper.getStateList(RegisterActivity.this,TAG,mDefCountryId);
            getSupportLoaderManager().restartLoader(mIDDefState, null, RegisterActivity.this);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private String checkValidation() {
        String message = null;
        if (etUsername.getText().toString().length() == 0) {
            message = getString(R.string.username_error);
        } else if (etEmail.getText().toString().length() == 0) {
            message = getString(R.string.email_error);
        } else if (!Utilities.isValidEmail(etEmail.getText().toString())) {
            message = getString(R.string.email_not_valid);
        } else if (etPassword.getText().toString().length() == 0) {
            message = getString(R.string.password_errors);
        } else if (etConfirmPassword.getText().toString().length() == 0) {
            message = getString(R.string.confirm_password_errors);
        } else if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
            message = getString(R.string.password_error);
        } else if (etSalutation.getText().toString().length() == 0) {
            message = getString(R.string.salutation);
        } else if (etFirstName.getText().toString().length() == 0) {
            message = getString(R.string.first_name_error);
        } else if (etLastName.getText().toString().length() == 0) {
            message = getString(R.string.last_name_error);
        } else if (etPhone.getText().toString().length() == 0) {
            message = getString(R.string.phone_error);
        } else if (etStreetAddress.getText().toString().length() == 0) {
            message = getString(R.string.street_address_errors);
        } else if (pressed == 0) {
            if (mEtDefAddress.getText().toString().length() == 0) {
                message = getString(R.string.address_error);
            } else if (mEtDefCity.getText().toString().length() == 0) {
                message = getString(R.string.city_error);
            } else if (mEtDefZipCode.getText().toString().length() == 0) {
                message = getString(R.string.postcode_error);
            }
        }
        return message;
    }

    private View.OnClickListener btnRegisterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Register register = new Register();
            if (checkValidation() == null) {
                Utilities.setFadeProgressBarVisibility(true, fadeProgressBar, progressBar);
                if (pressed == 0) {
                    register.setUsername(Utilities.encode(etUsername.getText().toString()));
                    register.setEmail(Utilities.encode(etEmail.getText().toString()));
                    register.setPassword(Utilities.encode(etPassword.getText().toString()));
                    register.setConfirmPassword(Utilities.encode(etConfirmPassword.getText().toString()));
                    register.setFirstName(Utilities.encode(etFirstName.getText().toString()));
                    register.setLastName(Utilities.encode(etLastName.getText().toString()));
                    register.setContactNo(Utilities.encode(etPhone.getText().toString()));
                    register.setAddress(Utilities.encode(etStreetAddress.getText().toString()));
                    register.setCity(Utilities.encode(etCity.getText().toString()));
                    register.setZipcode(Utilities.encode(etZipcode.getText().toString()));
                    if (mStateAdapter.getCount() != 0) {
                        register.setStateCode(Utilities.encode(mStateAdapter.getState(mSpineerState.getSelectedItemPosition()).getStateCode()));
                    }
                    register.setSalutation(Utilities.encode(etSalutation.getText().toString()));

                    View view = mSpinner.getSelectedView();
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    Country country = (Country) textView.getTag();
                    register.setDefAddress(Utilities.encode(mEtDefAddress.getText().toString()));
                    register.setDefCity(Utilities.encode(mEtDefCity.getText().toString()));
                    register.setDefZipcode(Utilities.encode(mEtDefZipCode.getText().toString()));
                    if (mStateDefAdapter.getCount() != 0) {
                        register.setDefStateCode(Utilities.encode(mStateDefAdapter.getState(mSpinnerDefState.getSelectedItemPosition()).getStateCode()));
                    }
                    register.setDefCountryId(Integer.valueOf(Utilities.encode(country.getId())));
                    register.setCountryId(Integer.valueOf(Utilities.encode(country.getId())));

                    HttpHelper.register(RegisterActivity.this, TAG, register);

                } else {
                    register.setUsername(Utilities.encode(etUsername.getText().toString()));
                    register.setEmail(Utilities.encode(etEmail.getText().toString()));
                    register.setPassword(Utilities.encode(etPassword.getText().toString()));
                    register.setConfirmPassword(Utilities.encode(etConfirmPassword.getText().toString()));
                    register.setFirstName(Utilities.encode(etFirstName.getText().toString()));
                    register.setLastName(Utilities.encode(etLastName.getText().toString()));
                    register.setContactNo(Utilities.encode(etPhone.getText().toString()));
                    register.setAddress(Utilities.encode(etStreetAddress.getText().toString()));
                    register.setCity(Utilities.encode(etCity.getText().toString()));
                    register.setZipcode(Utilities.encode(etZipcode.getText().toString()));
                    if (mStateAdapter.getCount() != 0) {
                        register.setStateCode(Utilities.encode(mStateAdapter.getState(mSpineerState.getSelectedItemPosition()).getStateCode()));
                    }
                    register.setSalutation(Utilities.encode(etSalutation.getText().toString()));

                    View view = mSpinner.getSelectedView();
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    Country country = (Country) textView.getTag();

                    register.setCountryId(Integer.valueOf(Utilities.encode(country.getId())));
                    register.setDefAddress(Utilities.encode(etStreetAddress.getText().toString()));
                    register.setDefCity(Utilities.encode(etCity.getText().toString()));
                    register.setDefZipcode(Utilities.encode(etZipcode.getText().toString()));
                    if (mStateDefAdapter.getCount() != 0) {
                        register.setDefStateCode(Utilities.encode(mStateAdapter.getState(mSpinnerDefState.getSelectedItemPosition()).getStateCode()));
                    }
                    register.setDefCountryId(Integer.valueOf(Utilities.encode(country.getId())));
                    register.setCountryId(Integer.valueOf(Utilities.encode(country.getId())));

                    HttpHelper.register(RegisterActivity.this, TAG, register);
                }
            } else {
                GeneralDialogFragment generalDialogFragment = GeneralDialogFragment.newInstance(RegisterActivity.this, checkValidation(), getString(R.string.warning));
                generalDialogFragment.show(getSupportFragmentManager(), "");
            }
        }
    };

    //region broadcast
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                if (intent.getBooleanExtra("failed", false)) {
                    Utilities.setFadeProgressBarVisibility(false, fadeProgressBar, progressBar);
                }
            }
        }
    };
    //endregion

    //region resume and pause
    @Override
    protected void onResume() {
        super.onResume();
        HttpHelper.getCountry(this, TAG);
        getSupportLoaderManager().initLoader(mLoaderId, null, this);
        com.applab.goodmorning.State.webapi.HttpHelper.getStateList(this,TAG,mCountryId);
        HttpHelper.getCountry(this,TAG);
        getSupportLoaderManager().initLoader(mLoaderId, null, this);
        getSupportLoaderManager().initLoader(mIDDefState, null, this);
        getSupportLoaderManager().initLoader(mIDState, null, this);
        IntentFilter intentFilter = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getBaseContext()).unregisterReceiver(broadcastReceiver);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (mLoaderId == id) {
            return new CursorLoader(RegisterActivity.this, CountryProvider.CONTENT_URI, null, null, null, null);
        } else if (mIDState == id) {
            return new CursorLoader(RegisterActivity.this, StateProvider.CONTENT_URI, null, DBHelper.STATE_COLUMN_COUNTRY_ID + "=?", new String[]{String.valueOf(mCountryId)}, null);
        } else if (mIDDefState == id) {
            return new CursorLoader(RegisterActivity.this, StateProvider.CONTENT_URI, null, DBHelper.STATE_COLUMN_COUNTRY_ID + "=?", new String[]{String.valueOf(mDefCountryId)}, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (mLoaderId == loader.getId()) {
            if (data != null) {
                if (data.getCount() > 0) {
                    ArrayList<Country> arrayList = new ArrayList<>();
                    for (int i = 0; i < data.getCount(); i++) {
                        Country country = Country.getCountry(data, i);
                        arrayList.add(country);
                    }
                    mSpinnerAdapter.swapData(arrayList);
                    mCountryId = mSpinnerAdapter.getCountry(0).getId();
                    getSupportLoaderManager().initLoader(mIDState, null, this);
                    getSupportLoaderManager().initLoader(mIDDefState, null, this);
                }
            }
        } else if (mIDState == loader.getId()) {
            if (data != null) {
                if (data.getCount() > 0) {
                    ArrayList<State> arrayList = new ArrayList<>();
                    for (int i = 0; i < data.getCount(); i++) {
                        State state = State.getStateItem(data, i);
                        arrayList.add(state);
                    }
                    mStateAdapter.swapData(arrayList);
                }else{
                    mStateDefAdapter.swapData(null);
                    mSpinnerDefState.setVisibility(View.INVISIBLE);
                }
            }else{
                mStateDefAdapter.swapData(null);
                mSpinnerDefState.setVisibility(View.INVISIBLE);
            }
        } else if (mIDDefState == loader.getId()) {
            if (data != null) {
                if (data.getCount() > 0) {
                    mSpinnerDefState.setVisibility(View.VISIBLE);
                    ArrayList<State> arrayList = new ArrayList<>();
                    for (int i = 0; i < data.getCount(); i++) {
                        State state = State.getStateItem(data, i);
                        arrayList.add(state);
                    }
                    mStateDefAdapter.swapData(arrayList);
                }else{
                    mStateDefAdapter.swapData(null);
                    mSpinnerDefState.setVisibility(View.INVISIBLE);
                }
            }else{
                mStateDefAdapter.swapData(null);
                mSpinnerDefState.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private View.OnClickListener mRlStateOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, StateActivity.class);
            intent.putExtra("countryId", mCountryId);
            startActivityForResult(intent, mRequestCode);
        }
    };

    private View.OnClickListener mRlDefStateOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, StateActivity.class);
            startActivityForResult(intent, mRequestCodeDef);
        }
    };
}