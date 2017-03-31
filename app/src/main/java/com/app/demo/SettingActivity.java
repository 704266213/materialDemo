package com.app.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("设置界面");
        initTile();
        request();
    }


    public int getMenu() {
        return R.menu.menu_setting;
    }



    Handler handler = new Handler(Looper.getMainLooper());
    String getUrl = "https://raw.githubusercontent.com/square/okhttp/master/README.md";
    public void request() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(getUrl).build();
        client.newCall(request).enqueue(new Callback() {

            public void onFailure(Call call, IOException e) {
                Log.e("TAG","====onFailure====================" + call.toString());
            }

            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG", "==response==================" + response.body().string());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onBack.setText("请求成功");
                    }
                });
            }
        });

    }
}


