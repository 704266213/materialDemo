package com.app.demo;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.demo.fragment.TabFragment;
import com.app.demo.widget.refresh.MaterialFrameLayout;
import com.app.demo.widget.refresh.PtrFrameLayout;
import com.app.demo.widget.refresh.PtrHandler;

import java.util.ArrayList;
import java.util.List;

public class MaterialActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    private List<ImageView> listviews;
    private ViewPager loopViewPager;
    private LoopAdapter loopAdapter;
    private int[] pics = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
    private Handler handler = new Handler();
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            init();
            loopAdapter.notifyDataSetChanged();
        }
    };


    private AppBarLayout appBarLayout;
    private MaterialFrameLayout ptrFrameLayout;
    public int verticalOffset;
    private Handler freshHandler = new Handler();
    private Runnable freshRun = new Runnable() {
        @Override
        public void run() {
            ptrFrameLayout.refreshComplete();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        setupToolbar();

        setupViewPager();

//        setListView();

        setupCollapsingToolbar();


        listviews = new ArrayList<>();
//        init();
        loopViewPager = (ViewPager) findViewById(R.id.loopViewPager);
        loopAdapter = new LoopAdapter();
        loopViewPager.setAdapter(loopAdapter);
        loopViewPager.addOnPageChangeListener(this);

        handler.postDelayed(run,10* 1000);

        appBarLayout = (AppBarLayout)findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("TAG","====Height==========" + appBarLayout.getHeight());
                MaterialActivity.this.verticalOffset = verticalOffset;
            }
        });
        ptrFrameLayout = (MaterialFrameLayout)findViewById(R.id.ptrFrameLayout);
        ptrFrameLayout.disableWhenHorizontalMove(true);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return verticalOffset==0 && ptrFrameLayout.checkContentCanBePulledDown(frame,content,header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                freshHandler.postDelayed(freshRun,5 * 1000);
            }
        });

    }

    private void setupCollapsingToolbar() {
//        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);

//        collapsingToolbar.setTitleEnabled(false);
//        //通过CollapsingToolbarLayout修改字体颜色
//        collapsingToolbar.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
//        collapsingToolbar.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色
    }

    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void  setListView(){
        List<String> listData = new ArrayList<>();
        for(int i = 0 ; i < 40 ; i++){
            listData.add("数据" + i);
        }

        final ListView listView = (ListView) findViewById(R.id.listView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        }
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listData));
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("TabbedCoordinatorLayout");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TabFragment(), "Tab 1");
        adapter.addFrag(new TabFragment(), "Tab 2");
        adapter.addFrag(new TabFragment(), "Tab 3");
        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }




    private void init(){
        int length = pics.length + 2;
        for (int i = 0; i < length; i++) {
            ImageView imageView = new ImageView(this);
            ViewGroup.LayoutParams viewPagerImageViewParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(viewPagerImageViewParams);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            listviews.add(imageView);
        }
    }

    public void onPageScrollStateChanged(int state) {
        int position = loopViewPager.getCurrentItem();
//        int realPosition = mAdapter.toRealPosition(position);
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            if (position == 0) {
                loopViewPager.setCurrentItem(loopAdapter.getCount() - 2, false);
            } else if (position == loopAdapter.getCount() - 1) {
                loopViewPager.setCurrentItem(1, false);
            }

        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset == 0) {
            if (position == 0) {
                loopViewPager.setCurrentItem(loopAdapter.getCount() - 2, false);
            } else if (position == loopAdapter.getCount() - 1) {
                loopViewPager.setCurrentItem(1, false);
            }
        }


    }

    @Override
    public void onPageSelected(int i) {

//        int pageIndex = i;

//        if (i == 0) {
//            // 当视图在第一个时，将页面号设置为图片的最后一张。
//            pageIndex = pics.length;
//        } else if (i == pics.length + 1) {
//            // 当视图在最后一个是,将页面号设置为图片的第一张。
//            pageIndex = 1;
//        }
//        if (i != pageIndex) {
//            loopViewPager.setCurrentItem(pageIndex, false);
//            return;
//        }
    }



    public class LoopAdapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView view = listviews.get(position);
            container.removeView(view);
            view.setImageBitmap(null);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int i) {
            if (i == 0) {
                listviews.get(i).setImageResource(pics[2]);
            } else if (i == (listviews.size() - 1)) {
                listviews.get(i).setImageResource(pics[0]);
            } else {
                listviews.get(i).setImageResource(pics[i - 1]);
            }
            container.addView(listviews.get(i));
            return listviews.get(i);
        }

        @Override
        public int getCount() {
            return listviews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loopViewPager.removeOnPageChangeListener(this);
    }
}
