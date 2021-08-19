package com.example.proyek2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashScreen extends AppCompatActivity {
    public static final int DELAY = 3000;

//    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if(preferences.getString("session", "").length()>0) {
//                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
//                    startActivity(intent);
//                }
//            }
//        }, DELAY);


        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        final Boolean isLoged = prefs.getBoolean("isLoged", false);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (isLoged == true) {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        timerThread.start();
    }
}