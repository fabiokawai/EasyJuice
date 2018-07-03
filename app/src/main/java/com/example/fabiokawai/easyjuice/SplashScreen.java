package com.example.fabiokawai.easyjuice;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                runApp();
            }
        }, 3000);
    }

    private void runApp(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}

