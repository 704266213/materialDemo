package com.app.demo.response;

import com.app.demo.Bean.BaseBean;

/**
 * Created by Administrator on 2016/4/20.
 */
public interface InitResponse<T extends BaseBean> {

     void onSuccess(T bean);
}
