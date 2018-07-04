package com.gabriel.borba.easyjuice.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.gabriel.borba.easyjuice.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Splash extends AppCompatActivity {

    private Handler handle = new Handler();
    private int progressStatus = 0 ;
    private ImageView imgprogress;
    AnimationDrawable juiceAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgprogress = findViewById(R.id.imgprogress);
        imgprogress.setBackgroundResource(R.drawable.juice);

        juiceAnimation = (AnimationDrawable) imgprogress.getBackground();

        juiceAnimation.start();
        handle.postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Splash.this.startActivity(new Intent(Splash.this, GroceryActivity.class));
                Splash.this.finish();
            }
        },3000);


    }
}
