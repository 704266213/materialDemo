package com.app.demo.adapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.demo.R;
import com.app.demo.widget.FixedViewpager;

import java.util.List;

/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/5/4 14:27
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    public static final int IS_HEADER = 2;
    public static final int IS_FOOTER = 3;
    public static final int IS_NORMAL = 1;

    List<String> mListData;
    private OnHeaderViewPageListener onHeaderViewPageListener;

    public RecyclerAdapter(List<String> mListData, OnHeaderViewPageListener onHeaderViewPageListener) {
        this.mListData = mListData;
        this.onHeaderViewPageListener = onHeaderViewPageListener;
    }

    public void addData(List<String> data) {
        mListData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 1 : mListData.size() + 1;
    }

    public int getItemViewType(int position) {
        if (position == 0) {
            return IS_HEADER;
        } else if (position > 0 && position < mListData.size()) {
            return IS_NORMAL;
        } else {
            return IS_FOOTER;
        }
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;
        if (viewType == IS_HEADER) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.headerview, viewGroup, false);
        } else if (viewType == IS_FOOTER) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footerview, viewGroup, false);
        } else if (viewType == IS_NORMAL) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        }
        return new RecyclerViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == IS_NORMAL) {
            recyclerViewHolder.title.setText(mListData.get(position));
        } else if (viewType == IS_HEADER) {

        } else {

        }

    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        FixedViewpager loopViewPager;

        public RecyclerViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == IS_NORMAL) {
                title = (TextView) itemView.findViewById(R.id.listitem_name);
            } else if (viewType == IS_HEADER) {
                loopViewPager = (FixedViewpager) itemView.findViewById(R.id.loopViewPager);
                if (onHeaderViewPageListener != null) {
                    onHeaderViewPageListener.onHeaderViewPageListener(loopViewPager);
                }

            } else {

            }
        }
    }

    public interface OnHeaderViewPageListener {

        void onHeaderViewPageListener(ViewPager loopViewPager);

    }

}
