package com.app.demo.response;

import android.os.Handler;
import android.os.Looper;

import com.app.demo.Bean.BaseBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/4/20 15:05
 */
public class AbstartCallBack<T extends BaseBean> implements Callback {


    Handler handler = new Handler(Looper.myLooper());

    private BaseParse baseParse;
    public AbstartCallBack(BaseParse baseParse){
        this.baseParse = baseParse;
    }


    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        T t = (T)baseParse.parseData(response.body().string());
        if(t.retcode == 0){
            handler.post(baseParse);
        }
    }
}
