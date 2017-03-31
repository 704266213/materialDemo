package com.app.demo;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initTile();

        Log.e("TAG", "====================" + toolbar);


        ImageView loading = (ImageView) findViewById(R.id.loading);
        final AnimationDrawable mAnimation = (AnimationDrawable) loading.getBackground();

        loading.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();
            }
        });

    }


}

