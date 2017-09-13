package com.applab.goodmorning.HomeScreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Welcome.activity.WelcomeActivity;

public class SplashScreenActivity extends AppCompatActivity {

    Handler timeHandler = new Handler();
    private String languageToLoad = "en_US";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        timeHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Utilities.getLanguage(SplashScreenActivity.this).equals("")) {
                    Utilities.saveLanguage(languageToLoad, SplashScreenActivity.this);
                }
                Utilities.loadLanguage(SplashScreenActivity.this);
                Intent intent = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        }, 1000);

    }
}
