package com.app.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.app.demo.widget.ViewPagerGroup;

public class ScollerViewPagerAcitivy extends AppCompatActivity implements View.OnClickListener {

    public static int screenWidth;  // 屏幕宽度
    public static int scrrenHeight;  //屏幕高度

    private Button bt_scrollLeft;
    private Button bt_scrollRight;
    private ViewPagerGroup mulTiViewGroup;
    private int curscreen = 0;   // 当前位于第几屏幕  ，共3个"屏幕"， 3个LinearLayout


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获得屏幕分辨率大小
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;
        scrrenHeight = metric.heightPixels;
        Log.e("TAG","screenWidth * scrrenHeight --->" + screenWidth + " * " + scrrenHeight);
        setContentView(R.layout.scoller_view_pager_acitivy);


        //获取自定义视图的空间引用
        mulTiViewGroup = (ViewPagerGroup) findViewById(R.id.mymultiViewGroup);

        bt_scrollLeft = (Button) findViewById(R.id.bt_scrollLeft);
        bt_scrollRight = (Button) findViewById(R.id.bt_scrollRight);

        bt_scrollLeft.setOnClickListener(this);
        bt_scrollRight.setOnClickListener(this);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.bt_scrollLeft:
                mulTiViewGroup.startMove();
                break;
            case R.id.bt_scrollRight:
                mulTiViewGroup.stopMove();
                break;
        }
    }

}
