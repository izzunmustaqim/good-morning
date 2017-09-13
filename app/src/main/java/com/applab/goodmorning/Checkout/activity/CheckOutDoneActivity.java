package com.applab.goodmorning.Checkout.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.applab.goodmorning.R;
import com.applab.goodmorning.Welcome.activity.WelcomeActivity;

public class CheckOutDoneActivity extends AppCompatActivity {
    private Button mBtnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_done);
        mBtnHome = (Button) findViewById(R.id.btnHome);
        mBtnHome.setOnClickListener(mBtnHomeOnClickListener);
    }

    private View.OnClickListener mBtnHomeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CheckOutDoneActivity.this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}
