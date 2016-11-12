package com.adfin.numobile.activity;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.content.Intent;
import android.telephony.TelephonyManager;
import java.io.IOException;
import android.util.Log;
import android.os.AsyncTask;

import com.adfin.numobile.R;

import com.adfin.numobile.utility.ClientThread;
import com.adfin.numobile.utility.Constant;
import com.adfin.numobile.utility.SessionManager;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SessionManager session = new SessionManager(getApplicationContext());

                Intent intent;
                intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}

