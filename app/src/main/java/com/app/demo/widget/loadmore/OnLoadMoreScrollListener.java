package com.app.demo.widget.loadmore;

import android.support.v7.widget.RecyclerView;


/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/8/24 17:48
 */
public class OnLoadMoreScrollListener extends RecyclerView.OnScrollListener {

    private LoadMoreListener loadMoreLinstener;
    public void setLoadMoreLinstener(LoadMoreListener loadMoreLinstener) {
        this.loadMoreLinstener = loadMoreLinstener;
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
//        lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
//        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
//            Log.e("TAG", "=========滚动到底部=============");
//            if(loadMoreLinstener != null)
//            loadMoreLinstener.onLoadMore();
//        }
    }

}
