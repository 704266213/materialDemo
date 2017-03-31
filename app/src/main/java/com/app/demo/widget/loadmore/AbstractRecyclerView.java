package com.app.demo.widget.loadmore;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/8/24 19:47
 */
public abstract class AbstractRecyclerView extends RecyclerView {

    public static final int TYPE_HEADERVIEW = 1;
    public static final int TYPE_FOOTERVIEW = 2;

    protected Adapter mAdapter;

    public AbstractRecyclerView(Context context) {
        super(context);
    }

    public AbstractRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbstractRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public class OnLoadMoreScrollListener extends RecyclerView.OnScrollListener {

        private int lastVisibleItem;
        private LoadMoreListener loadMoreListener;

        public OnLoadMoreScrollListener(LoadMoreListener loadMoreListener) {
            this.loadMoreListener = loadMoreListener;
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = getLinearLayoutManager().findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                Log.e("TAG", "=========滚动到底部=============");
                if (loadMoreListener != null)
                    loadMoreListener.onLoadMore();
            }
        }

    }


    public void setAdapter(Adapter adapter) {
        setLayoutManager();
        super.setAdapter(adapter);
        this.mAdapter = adapter;
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreLinstener) {
        addOnScrollListener(new OnLoadMoreScrollListener(loadMoreLinstener));
    }

    public abstract LinearLayoutManager getLinearLayoutManager();

    public abstract void setLayoutManager();

}
