package com.applab.goodmorning.SelectCountry.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;

public class SelectCountryActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private ImageButton mImage;
    private RelativeLayout mBtnMalaysia;
    private RelativeLayout mBtnSingapore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);
        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_select_your_country));

        mImage = (ImageButton) mToolbar.findViewById(R.id.img);
        mImage.setVisibility(View.GONE);

        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);

        mBtnMalaysia = (RelativeLayout) findViewById(R.id.btnMalaysia);
        mBtnSingapore = (RelativeLayout) findViewById(R.id.btnSingapore);

        mBtnMalaysia.setOnClickListener(mBtnMalaysiaClickListener);
        mBtnSingapore.setOnClickListener(mBtnSingaporeClickListener);
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener mBtnMalaysiaClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utilities.saveCountry(SelectCountryActivity.this, 1);
        }
    };

    private View.OnClickListener mBtnSingaporeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utilities.saveCountry(SelectCountryActivity.this, 2);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_country, menu);
        return true;
    }


}
