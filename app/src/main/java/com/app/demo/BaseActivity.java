package com.app.demo;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity{

    protected Toolbar toolbar;
    protected String title;
    protected TextView onBack;

    public void setTitle(String title) {
        this.title = title;
    }

    public void initTile(){
        onBack = (TextView)toolbar.findViewById(R.id.back);
        Log.e("TAG", "title ====================" + (title == null));
        title = title == null?getTitle().toString():title;
        onBack.setText(title);
        onBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.e("TAG", "title ====================" + getTitle());
                finish();
            }
        });

        

        int menu = getMenu();
        if( menu != 0){
            toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    // Handle the menu item
                    return true;
                }
            });

            // Inflate a menu to be displayed in the toolbar
            toolbar.inflateMenu(menu);
        }



    }


    public int getMenu(){
        return  0;
    }

}
