package com.app.demo.widget.loadmore;

/**
 * Created by Administrator on 2016/8/24.
 */
public interface LoadMoreUIHandler {

    void onLoading();

    void onLoadFinish(boolean hasMore);

    void onLoadError();

}
