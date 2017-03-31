package com.app.demo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.app.demo.adapter.LoopViewPagerAdapter;
import com.app.demo.widget.refresh.MaterialFrameLayout;
import com.app.demo.widget.refresh.PtrFrameLayout;
import com.app.demo.widget.refresh.PtrHandler;
import com.app.demo.Bean.Character;

import java.util.List;

public class CollapsingToolbarActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private MaterialFrameLayout ptrFrameLayout;
    public int verticalOffsetY;

    private Toolbar toolbar;
    private Drawable actionBarbackgroundDrawable;
    private TextView fixHead;
    private TextView title;

    private ViewPager viewPager;
    private ViewGroup indicators;
    private LoopViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBarbackgroundDrawable = toolbar.getBackground();
        actionBarbackgroundDrawable.setAlpha(0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        fixHead = (TextView) findViewById(R.id.fixHead);
        title = (TextView) findViewById(R.id.title);

        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int toolbarHeight = toolbar.getHeight();
                Log.e("TAG", "=====toolbarHeight==========" + toolbarHeight);
                Log.e("TAG", "=====verticalOffset==========" + verticalOffset);
                Log.e("TAG", "=====appBarLayout==========" + appBarLayout.getHeight());

                verticalOffsetY = verticalOffset;
                int absOffset = Math.abs(verticalOffset);
                if (absOffset < toolbarHeight) {
                    final float ratio = ((float) absOffset) / toolbarHeight;
                    final int newAlpha = (int) (ratio * 255);
                    actionBarbackgroundDrawable.setAlpha(newAlpha);
                    title.setVisibility(View.GONE);
                } else {
                    title.setVisibility(View.VISIBLE);
                }
//                if(absOffset ==  appBarLayout.getHeight() - fixHead.getHeight()) {
//                    actionBarbackgroundDrawable.setAlpha(0);
//                }

            }
        });


        ptrFrameLayout = (MaterialFrameLayout) findViewById(R.id.ptrFrameLayout);
        ptrFrameLayout.disableWhenHorizontalMove(true);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return verticalOffsetY == 0 && ptrFrameLayout.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(run, 5 * 1000);
            }
        });


        List<Character> characterList = JSON.parseArray(json, Character.class);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        indicators = (ViewGroup) findViewById(R.id.indicators);
        mPagerAdapter = new LoopViewPagerAdapter(viewPager, indicators);
        viewPager.setAdapter(mPagerAdapter);
        viewPager.addOnPageChangeListener(mPagerAdapter);
        mPagerAdapter.setList(characterList);
        viewPager.setBackgroundDrawable(getResources().getDrawable(R.mipmap.bg_viewpager));
        mPagerAdapter.start();
    }

    private Handler handler = new Handler();
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            ptrFrameLayout.refreshComplete();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    public void onResume() {
        super.onResume();
        if (mPagerAdapter != null) {
            mPagerAdapter.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPagerAdapter != null) {
            mPagerAdapter.stop();
        }
    }


    private String json = " [{" +
            "      \"avatar\": \"http://i.annihil.us/u/prod/marvel/i/mg/9/a0/54adb647b792d.png\"," +
            "      \"name\": \"Ant-Man\"" +
            "    }," +
            "    {" +
            "      \"avatar\": \"http://x.annihil.us/u/prod/marvel/i/mg/c/00/54adb7c4e163b.png\"," +
            "      \"name\": \"Black Panther\"" +
            "    }," +
            "    {" +
            "      \"avatar\": \"http://x.annihil.us/u/prod/marvel/i/mg/a/10/54adb9789bc28.png\"," +
            "      \"name\": \"Captain Marvel\"" +
            "    }," +
            "    {" +
            "      \"avatar\": \"http://x.annihil.us/u/prod/marvel/i/mg/b/40/54adba004fe21.png\"," +
            "      \"name\": \"Doctor Strange\"" +
            "    }," +
            "    {" +
            "      \"avatar\": \"http://i.annihil.us/u/prod/marvel/i/mg/f/40/54adba8b35f8b.png\"," +
            "      \"name\": \"Inhumans\"" +
            "    }]";

}
