package com.app.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ScollerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoller);
    }


    public void backGround(View v){
        Log.e("TAG", "============backGround=================");
    }

    public void front(View v){
        Log.e("TAG","============front=================");
    }

}