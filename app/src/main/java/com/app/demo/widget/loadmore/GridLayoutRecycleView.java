package com.app.demo.widget.loadmore;/**
 * Created by Administrator on 2016/8/25.
 */

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;


/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/8/25 11:05
 */
public class GridLayoutRecycleView extends AbstractRecyclerView {

    private int spanCount = 2;
    private GridLayoutManager gridLayoutManager;

    public GridLayoutRecycleView(Context context) {
        super(context);
    }

    public GridLayoutRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridLayoutRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    @Override
    public GridLayoutManager getLinearLayoutManager() {
        return gridLayoutManager;
    }

    @Override
    public void setLayoutManager() {
        gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int position) {
                int type = mAdapter.getItemViewType(position);
                return (type == TYPE_HEADERVIEW || type == TYPE_FOOTERVIEW) ? gridLayoutManager.getSpanCount() : 1;
            }
        });
    }
}
