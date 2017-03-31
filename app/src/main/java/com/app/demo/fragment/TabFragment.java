package com.app.demo.fragment;/**
 * Created by Administrator on 2016/5/4.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.demo.R;
import com.app.demo.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/5/4 14:23
 */
public class TabFragment extends Fragment implements ViewPager.OnPageChangeListener, RecyclerAdapter.OnHeaderViewPageListener {


    private RecyclerAdapter mAdapter;

    private String mItemData = "Lorem Ipsum is simply dummy text of the printing and "
            + "typesetting industry Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";


    private List<ImageView> listviews;
    private ViewPager loopViewPager;
    private LoopAdapter loopAdapter;
    private int[] pics = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
    private View rootView;
    private int lastVisibleItem;
    private List<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(
                R.id.fragment_list_rv);

//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(linearLayoutManager);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int position) {
                int type = mAdapter.getItemViewType(position);
                return (type == RecyclerAdapter.IS_HEADER || type == RecyclerAdapter.IS_FOOTER) ? gridLayoutManager.getSpanCount() : 1;
            }
        });

        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                     Log.e("TAG","=========滚动到底部=============");
                    handler.postDelayed(run,5 * 1000);
                }
            }
        });


        String[] listItems = mItemData.split(" ");

        list = new ArrayList<>();
        Collections.addAll(list, listItems);

        mAdapter = new RecyclerAdapter(list, this);
        recyclerView.setAdapter(mAdapter);
//        setListView(view);

        rootView.findViewById(R.id.emptyView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "===========emptyView==================");
            }
        });


        listviews = new ArrayList<>();
        init();
//        loopViewPager = (ViewPager) rootView.findViewById(R.id.loopViewPager);
//        loopAdapter = new LoopAdapter();
//        loopViewPager.setAdapter(loopAdapter);
//        loopViewPager.addOnPageChangeListener(this);

        return rootView;
    }


    @Override
    public void onHeaderViewPageListener(ViewPager loopViewPager) {
        this.loopViewPager = loopViewPager;
        loopAdapter = new LoopAdapter();
        loopViewPager.setAdapter(loopAdapter);
        loopViewPager.addOnPageChangeListener(this);
    }


    private void setListView(View view) {
        List<String> listData = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            listData.add("数据" + i);
        }

        final ListView listView = (ListView) view.findViewById(R.id.listView);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            listView.setNestedScrollingEnabled(true);
//        }
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listData));
    }


    private void init() {
        int length = pics.length + 2;
        for (int i = 0; i < length; i++) {
            ImageView imageView = new ImageView(getActivity());
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


    Handler handler = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            mAdapter.addData(list);
        }
    };

}
