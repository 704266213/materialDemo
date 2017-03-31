package com.app.demo.widget.loadmore;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/8/25 14:10
 */
public abstract class AbstractRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LoadMoreFooterView loadMoreFooterView;
    public LoadMoreFooterView getLoadMoreFooterView() {
        return loadMoreFooterView;
    }

    @Override
    public int getItemCount() {
        int count = getCount();
        return count == 0 ? 0 : count + 1;
    }

    public int getItemViewType(int position) {
        if (position == getCount()) {
            return AbstractRecyclerView.TYPE_FOOTERVIEW;
        } else {
            return getViewType(position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AbstractRecyclerView.TYPE_FOOTERVIEW) {
            loadMoreFooterView = new LoadMoreFooterView(parent.getContext());
            return new FooterViewHolder(loadMoreFooterView);
        } else {
            return onCreateRealViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != AbstractRecyclerView.TYPE_FOOTERVIEW) {
            onBindDataToViewHolder(holder, position);
        }
    }

    public abstract int getCount();

    public abstract int getViewType(int position);

    public abstract RecyclerView.ViewHolder onCreateRealViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindDataToViewHolder(RecyclerView.ViewHolder holder, int position);


    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(LoadMoreFooterView itemView) {
            super(itemView);
        }
    }

}
