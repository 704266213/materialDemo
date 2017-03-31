package com.app.demo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by ryan on 1/6/2016.
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }


    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }


}
