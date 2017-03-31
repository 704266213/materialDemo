package com.app.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.demo.widget.HeadViewPager;

import java.util.ArrayList;
import java.util.List;

public class HeadViewPagerActivity extends AppCompatActivity implements HeadViewPager.OnScollerListener {

    private ListView listView;
    private HeadViewPager headViewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_view_pager);

        headViewPager = (HeadViewPager)findViewById(R.id.headViewPager);
        headViewPager.setOnScollerListener(this);

        listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                getData());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG","========================" + position);
            }
        });
    }


    private List<String> getData() {
        List<String> list = new ArrayList<>();
        for(int i = 0 ;  i < 40 ; i++){
            list.add("房子 " + i);
        }
        return list;
    }


    public void fixHeadView(View v){
        Log.e("TAG", "=============fixHeadView=================" );
    }

    public void headView(View v){
        Log.e("TAG", "=============headView=================" );
    }


    @Override
    public boolean isOnTop() {
            int firstVisiblePosition = listView.getFirstVisiblePosition();
            View childAt = listView.getChildAt(0);
            if (childAt == null || (firstVisiblePosition == 0 && childAt != null && childAt.getTop() == 0)) {
                return true;
            }
        return false;
    }
}
