package com.applab.goodmorning.EditDetails.activity;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.applab.goodmorning.Account.model.Account;
import com.applab.goodmorning.EditDetails.adapter.ListSpinnerAdapter;
import com.applab.goodmorning.EditDetails.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.adapter.CountryListAdapter;
import com.applab.goodmorning.Register.adapter.StateListAdapter;
import com.applab.goodmorning.Register.model.Country;
import com.applab.goodmorning.Register.model.Register;
import com.applab.goodmorning.Register.provider.CountryProvider;
import com.applab.goodmorning.State.activity.StateActivity;
import com.applab.goodmorning.State.model.State;
import com.applab.goodmorning.State.provider.StateProvider;
import com.applab.goodmorning.Utilities.DBHelper;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Utilities.dialog.GeneralDialogFragment;

import java.util.ArrayList;

public class EditDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private ImageButton mImg;
    private LinearLayout mCboCheck;
    private ImageView mImgCbo;
    private int pressed;
    private CountryListAdapter mSpinnerCountryAdapter;
    private CountryListAdapter mSpinnerShippingCountryAdapter;
    private StateListAdapter mSpinnerStateAdapter;
    private StateListAdapter mSpinnerShippingStateAdapter;
    private Spinner mSpinnerCountry;
    private Spinner mSpinnerShippingCountry;
    private RelativeLayout btnSubmitEdit;
    private String TAG = EditDetailsActivity.class.getSimpleName();
    private EditText etSalutation, etFirstName, etLastName, etPhone, etAddress, etCity, etPostCode, etDefAddress;
    private EditText etShippingAddress, etShippingCity, etShippingPostCode, etShippingState;
    private Account mAccount;
    private int mRequestCode = 2421;
    private int mRequestCodeDef = 3531;
    private CardView cvShippingAddress;
    private LinearLayout mLinearShippingAddress;

    private int mCountryId = 1;
    private int mDefCountryId = 1;
    private int mIDState = 4245;
    private int mIDDefState = 4425;
    private int mLoaderId = 3516;

    private RelativeLayout fadeProgressBar;
    private ProgressBar progressBar;


    private Spinner mSpinnerState, mSpinnerShippingState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        etSalutation = (EditText) findViewById(R.id.etSalutation);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etCity = (EditText) findViewById(R.id.etCity);
        etPostCode = (EditText) findViewById(R.id.etPostCode);
        mAccount = getIntent().getParcelableExtra("Details");

        etShippingAddress = (EditText) findViewById(R.id.etShippingAddress);
        etShippingCity = (EditText) findViewById(R.id.etShippingCity);
        etShippingPostCode = (EditText) findViewById(R.id.etShippingPostCode);
        btnSubmitEdit = (RelativeLayout) findViewById(R.id.btnSubmitEdit);
        cvShippingAddress = (CardView) findViewById(R.id.cvShippingAddress);
        cvShippingAddress = (CardView) findViewById(R.id.cardShippingAddress);
        mLinearShippingAddress = (LinearLayout) findViewById(R.id.linearShippingAddress);

        fadeProgressBar = (RelativeLayout) findViewById(R.id.fadeProgressBar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Utilities.setFadeProgressBarVisibility(false, fadeProgressBar, progressBar);

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mToolbar.setFitsSystemWindows(false);
        mToolbar.setPadding(0, 0, 0, 0);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_edit_details));

        mImg = (ImageButton) mToolbar.findViewById(R.id.img);
        mImg.setVisibility(View.GONE);

        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);

        mCboCheck = (LinearLayout) findViewById(R.id.cboCheck);
        mImgCbo = (ImageView) findViewById(R.id.imgCbo);

        mCboCheck.setOnClickListener(mCboCheckOnClickListener);

        mSpinnerCountry = (Spinner) findViewById(R.id.spinner_country);
        mSpinnerShippingCountry = (Spinner) findViewById(R.id.spinner_shipping_country);
        mSpinnerCountryAdapter = new CountryListAdapter(new ArrayList<Country>(), this, false);
        mSpinnerShippingCountryAdapter = new CountryListAdapter(new ArrayList<Country>(), this, false);
        mSpinnerCountry.setAdapter(mSpinnerCountryAdapter);
        mSpinnerShippingCountry.setAdapter(mSpinnerShippingCountryAdapter);

        mSpinnerState = (Spinner) findViewById(R.id.spinner_state);
        mSpinnerStateAdapter = new StateListAdapter(new ArrayList<State>(), this, false);
        mSpinnerState.setAdapter(mSpinnerStateAdapter);
        mSpinnerShippingState = (Spinner) findViewById(R.id.spinner_shipping_state);
        mSpinnerShippingStateAdapter = new StateListAdapter(new ArrayList<State>(), this, false);
        mSpinnerShippingState.setAdapter(mSpinnerShippingStateAdapter);

        btnSubmitEdit.setOnClickListener(btnSubmitEditOnClickListener);

        mSpinnerCountry.setOnItemSelectedListener(mSpinnerCountryItemListener);
        mSpinnerShippingCountry.setOnItemSelectedListener(mDefSpinnerItemListener);

        setDetails();
    }

    private Spinner.OnItemSelectedListener mSpinnerCountryItemListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mCountryId = mSpinnerCountryAdapter.getCountry(position).getId();
            com.applab.goodmorning.State.webapi.HttpHelper.getStateList(EditDetailsActivity.this, TAG, mCountryId);
            getSupportLoaderManager().restartLoader(mIDState, null, EditDetailsActivity.this);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private Spinner.OnItemSelectedListener mDefSpinnerItemListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mDefCountryId = mSpinnerShippingCountryAdapter.getCountry(position).getId();
            com.applab.goodmorning.State.webapi.HttpHelper.getStateList(EditDetailsActivity.this, TAG, mDefCountryId);
            getSupportLoaderManager().restartLoader(mIDDefState, null, EditDetailsActivity.this);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private String checkValidation() {
        String message = null;
        if (etSalutation.getText().toString().length() == 0) {
            message = getString(R.string.salutation);
        } else if (etFirstName.getText().toString().length() == 0) {
            message = getString(R.string.first_name_error);
        } else if (etLastName.getText().toString().length() == 0) {
            message = getString(R.string.last_name_error);
        } else if (etPhone.getText().toString().length() == 0) {
            message = getString(R.string.phone_error);
        } else if (etAddress.getText().toString().length() == 0) {
            message = getString(R.string.street_address_errors);
        } else if (pressed == 0) {
            if (etShippingAddress.getText().toString().length() == 0) {
                message = getString(R.string.address_error);
            } else if (etShippingCity.getText().toString().length() == 0) {
                message = getString(R.string.city_error);
            } else if (etShippingPostCode.getText().toString().length() == 0) {
                message = getString(R.string.postcode_error);
            }
        }
        return message;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        com.applab.goodmorning.Register.webapi.HttpHelper.getCountry(this, TAG);
        com.applab.goodmorning.State.webapi.HttpHelper.getStateList(this,TAG,mCountryId);
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
            return new CursorLoader(EditDetailsActivity.this, CountryProvider.CONTENT_URI, null, null, null, null);
        } else if (mIDState == id) {
            return new CursorLoader(EditDetailsActivity.this, StateProvider.CONTENT_URI, null, DBHelper.STATE_COLUMN_COUNTRY_ID + "=?", new String[]{String.valueOf(mCountryId)}, null);
        } else if (mIDDefState == id) {
            return new CursorLoader(EditDetailsActivity.this, StateProvider.CONTENT_URI, null, DBHelper.STATE_COLUMN_COUNTRY_ID + "=?", new String[]{String.valueOf(mDefCountryId)}, null);
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
                    mSpinnerCountryAdapter.swapData(arrayList);
                    for (int j = 0; j < data.getCount(); j++) {
                        Country country = Country.getCountry(data, j);
                        if (mAccount != null) {
                            if (mAccount.getCountryId().equals(country.getId())) {
                                mSpinnerCountry.setSelection(j);
                            }
                        }
                    }
                    mSpinnerShippingCountryAdapter.swapData(arrayList);
                    mCountryId = mSpinnerCountryAdapter.getCountry(0).getId();
                    com.applab.goodmorning.State.webapi.HttpHelper.getStateList(EditDetailsActivity.this, TAG, mCountryId);
                }
            }
        } else if (mIDState == loader.getId()) {
            if (data != null) {
                if (data.getCount() > 0) {
                    mSpinnerState.setVisibility(View.VISIBLE);
                    ArrayList<State> arrayList = new ArrayList<>();
                    for (int i = 0; i < data.getCount(); i++) {
                        State state = State.getStateItem(data, i);
                        arrayList.add(state);
                    }
                    mSpinnerStateAdapter.swapData(arrayList);
                    if (mAccount != null) {
                        for (int i = 0; i < data.getCount(); i++) {
                            State state = State.getStateItem(data, i);
                            if (mAccount.getStateCode().equals(state.getStateCode())) {
                                mSpinnerState.setSelection(i);
                            }
                        }
                    }
                } else {
                    mSpinnerStateAdapter.swapData(null);
                    mSpinnerState.setVisibility(View.INVISIBLE);
                }
            } else {
                mSpinnerStateAdapter.swapData(null);
                mSpinnerState.setVisibility(View.INVISIBLE);
            }
        } else if (mIDDefState == loader.getId()) {
            if (data != null) {
                if (data.getCount() > 0) {
                    mSpinnerShippingState.setVisibility(View.VISIBLE);
                    ArrayList<State> arrayList = new ArrayList<>();
                    for (int i = 0; i < data.getCount(); i++) {
                        State state = State.getStateItem(data, i);
                        arrayList.add(state);
                    }
                    mSpinnerShippingStateAdapter.swapData(arrayList);
                } else {
                    mSpinnerShippingStateAdapter.swapData(null);
                    mSpinnerShippingState.setVisibility(View.INVISIBLE);
                }
            } else {
                mSpinnerShippingStateAdapter.swapData(null);
                mSpinnerShippingState.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

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
                mImgCbo.setImageResource(R.drawable.check_1_after);
                mLinearShippingAddress.setVisibility(View.GONE);
                pressed = 1;
            } else {
                mImgCbo.setImageResource(R.drawable.check_1_before);
                mLinearShippingAddress.setVisibility(View.VISIBLE);
                pressed = 0;
            }
        }
    };

    private View.OnClickListener btnSubmitEditOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkValidation() == null) {
                Utilities.setFadeProgressBarVisibility(true, fadeProgressBar, progressBar);
                if (pressed == 0) {
                    String salutation = etSalutation.getText().toString();
                    String firstName = etFirstName.getText().toString();
                    String lastName = etLastName.getText().toString();
                    String phone = etPhone.getText().toString();
                    String address = etAddress.getText().toString();
                    String city = etCity.getText().toString();
                    String postcode = etPostCode.getText().toString();

                    String state = "";
                    if (mSpinnerStateAdapter.getCount() != 0) {
                        state = mSpinnerStateAdapter.getState(mSpinnerState.getSelectedItemPosition()).getStateCode();
                    }
                    //String state = mTxtState.getTag().toString() == null ? null : mTxtState.getTag().toString();
                    String shipAddress = etShippingAddress.getText().toString();
                    String shipCity = etShippingCity.getText().toString();
                    String shipPostCode = etPostCode.getText().toString();
                    String shipState = "";

                    if (mSpinnerShippingStateAdapter.getCount() != 0) {
                        shipState = mSpinnerStateAdapter.getState(mSpinnerShippingState.getSelectedItemPosition()).getStateCode();
                    }

                    Utilities.setCountryId(EditDetailsActivity.this, mSpinnerCountry.getSelectedItemPosition());
                    Register register = new Register();
                    register.setSalutation(Utilities.encode(salutation));
                    register.setFirstName(Utilities.encode(firstName));
                    register.setLastName(Utilities.encode(lastName));
                    register.setContactNo(Utilities.encode(phone));
                    register.setAddress(Utilities.encode(address));
                    register.setCity(Utilities.encode(city));
                    register.setZipcode(Utilities.encode(postcode));
                    register.setCountryId(Integer.valueOf(Utilities.encode(mSpinnerCountry.getSelectedItemPosition())));
                    register.setStateCode(Utilities.encode(state));

                    register.setDefAddress(Utilities.encode(shipAddress));
                    register.setDefCity(Utilities.encode(shipCity));
                    register.setDefZipcode(Utilities.encode(shipPostCode));
                    register.setDefStateCode(Utilities.encode(shipState));
                    register.setDefCountryId(Integer.valueOf(Utilities.encode(mSpinnerCountry.getSelectedItemPosition())));

                    HttpHelper.editDetails(EditDetailsActivity.this, TAG, register);
                } else {
                    String salutation = etSalutation.getText().toString();
                    String firstName = etFirstName.getText().toString();
                    String lastName = etLastName.getText().toString();
                    String phone = etPhone.getText().toString();
                    String address = etAddress.getText().toString();
                    String city = etCity.getText().toString();
                    String postcode = etPostCode.getText().toString();
                    String state = "";
                    if (mSpinnerStateAdapter.getCount() != 0) {
                        state = mSpinnerStateAdapter.getState(mSpinnerState.getSelectedItemPosition()).getStateCode();
                    }

                    String shipAddress = etAddress.getText().toString();
                    String shipCity = etCity.getText().toString();
                /*
                String shipState = "";
                if (mTxtState.getTag() != null) {
                    shipState = mTxtState.getTag().toString() == null ? null : mTxtState.getTag().toString();
                } */

                    String shipPostCode = etPostCode.getText().toString();

                    Register register = new Register();
                    register.setSalutation(Utilities.encode(salutation));
                    register.setFirstName(Utilities.encode(firstName));
                    register.setLastName(Utilities.encode(lastName));
                    register.setContactNo(Utilities.encode(phone));
                    register.setAddress(Utilities.encode(address));
                    register.setCity(Utilities.encode(city));
                    register.setZipcode(Utilities.encode(postcode));
                    register.setStateCode(Utilities.encode(state));
                    register.setCountryId(Integer.valueOf(Utilities.encode(mSpinnerCountry.getSelectedItemPosition())));

                    register.setDefAddress(Utilities.encode(shipAddress));
                    register.setDefCity(Utilities.encode(shipCity));
                    register.setDefZipcode(Utilities.encode(shipPostCode));
                    register.setDefStateCode(Utilities.encode(state));
                    register.setDefCountryId(Integer.valueOf(Utilities.encode(mSpinnerCountry.getSelectedItemPosition())));

                    HttpHelper.editDetails(EditDetailsActivity.this, TAG, register);
                }
            } else {
                GeneralDialogFragment generalDialogFragment = GeneralDialogFragment.newInstance(EditDetailsActivity.this, checkValidation(), getString(R.string.warning));
                generalDialogFragment.show(getSupportFragmentManager(), "");
            }
        }
    };

    private void setDetails() {
        etSalutation.setText(mAccount.getSalutation());
        etFirstName.setText(mAccount.getFirstName());
        etLastName.setText(mAccount.getLastName());
        etAddress.setText(mAccount.getAddress());
        etPhone.setText(mAccount.getContactNo());
        etCity.setText(mAccount.getCity());
        etPostCode.setText(mAccount.getZipcode());

        etShippingAddress.setText(mAccount.getDefAddress());
        etShippingCity.setText(mAccount.getDefCity());
        etShippingPostCode.setText(mAccount.getDefZipcode());
    }
}
