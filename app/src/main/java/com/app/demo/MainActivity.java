package com.app.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.demo.widget.CollapsingToolbarLayoutActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void login(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void setting(View v) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void test(View v) {
        Intent intent = new Intent(this, ScollerActivity.class);
        startActivity(intent);
    }


    public void headViewPager(View v) {
        Intent intent = new Intent(this, HeadViewPagerActivity.class);
        startActivity(intent);
    }


    public void material(View v) {
        Intent intent = new Intent(this, MaterialActivity.class);
        startActivity(intent);
    }

    public void myViewGroup(View v) {
        Intent intent = new Intent(this,MyViewGroupActivity.class);
        startActivity(intent);

    }

    public void scollerViewPager(View v) {
        Intent intent = new Intent(this, ScollerViewPagerAcitivy.class);
        startActivity(intent);
    }


    public void collapsingToolbarLayout(View v) {
        Intent intent = new Intent(this, CollapsingToolbarActivity.class);
        startActivity(intent);
    }

    public void collapsingToolbar(View v) {
        Intent intent = new Intent(this, CollapsingToolbarLayoutActivity.class);
        startActivity(intent);
    }

    public void stickyHeadersRecyclerView(View v) {
        Intent intent = new Intent(this, StickyHeadersRecyclerViewActivity.class);
        startActivity(intent);
    }

    public void onMaterialScoller(View v) {
        Intent intent = new Intent(this, MaterialScollerActivity.class);
        startActivity(intent);
    }

}
